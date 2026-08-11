//
// This source file is part of the ENGAGE-HF Android open-source project
//
// SPDX-FileCopyrightText: 2025 Stanford University and the project authors (see CONTRIBUTORS.md)
//
// SPDX-License-Identifier: MIT
//

package edu.stanford.bdh.engagehf.modules.onboarding.invitation

import edu.stanford.spezi.ui.StringResource

data class InvitationCodeViewData(
    val description: StringResource,
    val redeemAction: () -> Unit,
)
