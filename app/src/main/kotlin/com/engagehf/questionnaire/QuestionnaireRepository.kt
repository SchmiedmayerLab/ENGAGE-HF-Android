//
// This source file is part of the ENGAGE-HF Android open-source project
//
// SPDX-FileCopyrightText: 2025 Stanford University and the project authors (see CONTRIBUTORS.md)
//
// SPDX-License-Identifier: MIT
//

package com.engagehf.questionnaire

import com.engagehf.modules.core.coroutines.Dispatching
import com.engagehf.modules.core.logging.engageLogger
import com.engagehf.modules.healthconnectonfhir.QuestionnaireDocumentMapper
import com.engagehf.observations.ObservationCollection
import com.engagehf.observations.ObservationCollectionProvider
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import org.hl7.fhir.r4.model.Questionnaire
import org.hl7.fhir.r4.model.QuestionnaireResponse
import javax.inject.Inject

class QuestionnaireRepository @Inject constructor(
    @Dispatching.IO private val ioDispatcher: CoroutineDispatcher,
    private val questionnaireDocumentMapper: QuestionnaireDocumentMapper,
    private val observationCollectionProvider: ObservationCollectionProvider,
    private val firestore: FirebaseFirestore,
) {
    private val logger by engageLogger()

    suspend fun getQuestionnaire(id: String): Result<Questionnaire> {
        return withContext(ioDispatcher) {
            runCatching {
                val document = firestore.collection(QUESTIONNAIRE_COLLECTION)
                    .document(id)
                    .get()
                    .await()
                questionnaireDocumentMapper.map(document)
            }.onFailure { exception ->
                logger.e(exception) { "Error fetching questionnaire" }
            }
        }
    }

    suspend fun save(questionnaireResponse: QuestionnaireResponse): Result<Unit> {
        return withContext(ioDispatcher) {
            runCatching {
                val document = questionnaireDocumentMapper.map(questionnaireResponse)
                observationCollectionProvider.getCollection(ObservationCollection.QUESTIONNAIRE)
                    .add(document).await().let { }
            }
        }
    }

    companion object {
        private const val QUESTIONNAIRE_COLLECTION = "questionnaires"
    }
}
