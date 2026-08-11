//
// This source file is part of the ENGAGE-HF Android open-source project
//
// SPDX-FileCopyrightText: 2025 Stanford University and the project authors (see CONTRIBUTORS.md)
//
// SPDX-License-Identifier: MIT
//

package edu.stanford.bdh.engagehf.modules.healthconnectonfhir

import com.google.firebase.firestore.DocumentSnapshot
import org.hl7.fhir.r4.model.Questionnaire
import org.hl7.fhir.r4.model.QuestionnaireResponse

interface QuestionnaireDocumentMapper {
    fun map(questionnaireResponse: QuestionnaireResponse): Map<String, Any>
    fun map(questionnaireDocument: DocumentSnapshot): Questionnaire
}
