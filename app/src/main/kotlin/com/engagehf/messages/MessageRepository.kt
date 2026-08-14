//
// This source file is part of the ENGAGE-HF Android open-source project
//
// SPDX-FileCopyrightText: 2025 Stanford University and the project authors (see CONTRIBUTORS.md)
//
// SPDX-License-Identifier: MIT
//

package com.engagehf.messages

import com.engagehf.modules.account.manager.UserSessionManager
import com.engagehf.modules.core.coroutines.Dispatching
import com.engagehf.modules.core.logging.engageLogger
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ListenerRegistration
import com.google.firebase.functions.FirebaseFunctions
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MessageRepository @Inject constructor(
    private val firestore: FirebaseFirestore,
    private val firebaseFunctions: FirebaseFunctions,
    private val userSessionManager: UserSessionManager,
    private val firestoreMessageMapper: FirestoreMessageMapper,
    @Dispatching.IO private val ioScope: CoroutineScope,
    @Dispatching.IO private val ioDispatcher: CoroutineDispatcher,
) {
    private val logger by engageLogger()

    fun observeUserMessages(): Flow<List<Message>> = callbackFlow {
        var listenerRegistration: ListenerRegistration? = null
        ioScope.launch {
            runCatching {
                val uid = userSessionManager.getUserUid()
                    ?: throw IllegalStateException("User not authenticated")
                listenerRegistration = firestore.collection("users")
                    .document(uid)
                    .collection("messages")
                    .whereEqualTo("completionDate", null)
                    .addSnapshotListener { value, error ->
                        if (error != null) {
                            logger.e(error) { "Error listening for user messages" }
                            return@addSnapshotListener
                        }
                        value?.documents?.mapNotNull { document ->
                            firestoreMessageMapper.map(document)
                        }?.let {
                            logger.i { "Sending messages list update of size: ${it.size}" }
                            trySend(it)
                        }
                    }
            }.onFailure {
                logger.e(it) { "Error while listening for user messages" }
            }
        }
        awaitClose {
            listenerRegistration?.remove()
            channel.close()
        }
    }

    suspend fun dismissMessage(messageId: String) = withContext(ioDispatcher) {
        runCatching {
            val uid = userSessionManager.getUserUid()
                ?: throw IllegalStateException("User not authenticated")
            val params = mapOf(
                "userId" to uid,
                "messageId" to messageId,
            )
            firebaseFunctions.getHttpsCallable("dismissMessage")
                .call(params)
                .await()

            logger.i { "Message completion for $messageId finished successfully" }
        }.onFailure {
            logger.e(it) { "Error while completing message with id $messageId" }
        }
    }
}
