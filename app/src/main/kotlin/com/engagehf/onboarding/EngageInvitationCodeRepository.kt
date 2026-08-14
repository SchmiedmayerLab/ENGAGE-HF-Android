//
// This source file is part of the ENGAGE-HF Android open-source project
//
// SPDX-FileCopyrightText: 2025 Stanford University and the project authors (see CONTRIBUTORS.md)
//
// SPDX-License-Identifier: MIT
//

package com.engagehf.onboarding

import com.engagehf.R
import com.engagehf.modules.navigation.Navigator
import com.engagehf.modules.onboarding.invitation.InvitationCodeRepository
import com.engagehf.modules.onboarding.invitation.InvitationCodeViewData
import com.engagehf.modules.ui.StringResource
import com.engagehf.navigation.AppNavigationEvent
import javax.inject.Inject

// TODO: Clarify / unify repositories or content provider apis
//      1. Single data object with lambda properties - reference EngageInvitationCodeRepository
//      2. One method to return static content, and additional methods to be invoked for an action
//      reference EngageConsentManager.kt
class EngageInvitationCodeRepository @Inject constructor(
    private val navigator: Navigator,
) : InvitationCodeRepository {

    override fun getScreenData(): InvitationCodeViewData {
        return InvitationCodeViewData(
            description = StringResource(R.string.invitation_code_description_message),
            redeemAction = { navigator.navigateTo(AppNavigationEvent.AppScreen(true)) },
        )
    }
}
