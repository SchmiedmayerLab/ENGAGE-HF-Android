//
// This source file is part of the ENGAGE-HF Android open-source project
//
// SPDX-FileCopyrightText: 2025 Stanford University and the project authors (see CONTRIBUTORS.md)
//
// SPDX-License-Identifier: MIT
//

package com.engagehf.modules.questionnaire

import org.hl7.fhir.r4.model.QuestionnaireResponse

sealed interface QuestionnaireResult {
    data object Cancelled : QuestionnaireResult
    data object Failed : QuestionnaireResult
    data class Completed(val response: QuestionnaireResponse) : QuestionnaireResult
}
