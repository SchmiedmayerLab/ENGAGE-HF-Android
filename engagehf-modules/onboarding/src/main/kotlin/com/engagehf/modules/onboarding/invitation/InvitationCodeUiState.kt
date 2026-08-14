//
// This source file is part of the ENGAGE-HF Android open-source project
//
// SPDX-FileCopyrightText: 2025 Stanford University and the project authors (see CONTRIBUTORS.md)
//
// SPDX-License-Identifier: MIT
//

package com.engagehf.modules.onboarding.invitation

import com.engagehf.modules.ui.StringResource

data class InvitationCodeUiState(
    val description: StringResource = StringResource(""),
    val invitationCode: String = "",
    val error: StringResource? = null,
)

sealed interface Action {
    data class UpdateInvitationCode(val invitationCode: String) : Action

    data object ClearError : Action

    data object RedeemInvitationCode : Action
}
