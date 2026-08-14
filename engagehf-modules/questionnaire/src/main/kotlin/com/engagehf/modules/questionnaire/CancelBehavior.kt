//
// This source file is part of the ENGAGE-HF Android open-source project
//
// SPDX-FileCopyrightText: 2025 Stanford University and the project authors (see CONTRIBUTORS.md)
//
// SPDX-License-Identifier: MIT
//

package com.engagehf.modules.questionnaire

sealed interface CancelBehavior {
    data object Disabled : CancelBehavior
    data object ShouldConfirmCancel : CancelBehavior
    data object Cancel : CancelBehavior
}
