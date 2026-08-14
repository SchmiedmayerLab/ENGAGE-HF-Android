//
// This source file is part of the ENGAGE-HF Android open-source project
//
// SPDX-FileCopyrightText: 2025 Stanford University and the project authors (see CONTRIBUTORS.md)
//
// SPDX-License-Identifier: MIT
//

package com.engagehf.observations

import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.engagehf.modules.account.manager.UserSessionManager
import javax.inject.Inject

class ObservationCollectionProvider @Inject constructor(
    private val userSessionManager: UserSessionManager,
    private val firestore: FirebaseFirestore,
) {

    @Throws(IllegalStateException::class)
    fun getCollection(collection: ObservationCollection): CollectionReference {
        val uid = userSessionManager.getUserUid() ?: error("User not authenticated")
        return firestore.collection("users/$uid/${collection.collectionName}")
    }
}
