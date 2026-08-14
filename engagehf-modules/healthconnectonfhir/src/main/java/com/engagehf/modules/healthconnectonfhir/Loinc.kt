//
// This source file is part of the ENGAGE-HF Android open-source project
//
// SPDX-FileCopyrightText: 2025 Stanford University and the project authors (see CONTRIBUTORS.md)
//
// SPDX-License-Identifier: MIT
//

package com.engagehf.modules.healthconnectonfhir

enum class Loinc(val code: String) {
    BLOOD_PRESSURE(code = "85354-9"),
    WEIGHT(code = "29463-7"),
    HEART_RATE(code = "8867-4"),
    QUESTIONNAIRE(code = "86923-0"),
}
