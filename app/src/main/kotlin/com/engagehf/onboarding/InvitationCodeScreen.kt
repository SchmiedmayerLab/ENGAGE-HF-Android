//
// This source file is part of the ENGAGE-HF Android open-source project
//
// SPDX-FileCopyrightText: 2025 Stanford University and the project authors (see CONTRIBUTORS.md)
//
// SPDX-License-Identifier: MIT
//

package com.engagehf.onboarding

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.engagehf.modules.onboarding.R
import com.engagehf.modules.onboarding.invitation.InvitationCodeView
import com.engagehf.modules.ui.CommonScaffold

@Composable
fun InvitationCodeScreen() {
    CommonScaffold(title = stringResource(R.string.onboarding_invitation_code)) {
        InvitationCodeView()
    }
}
