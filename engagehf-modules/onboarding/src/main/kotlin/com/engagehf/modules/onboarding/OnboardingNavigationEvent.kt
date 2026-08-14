//
// This source file is part of the ENGAGE-HF Android open-source project
//
// SPDX-FileCopyrightText: 2025 Stanford University and the project authors (see CONTRIBUTORS.md)
//
// SPDX-License-Identifier: MIT
//

package com.engagehf.modules.onboarding

import com.engagehf.modules.navigation.NavigationEvent

sealed class OnboardingNavigationEvent : NavigationEvent {

    data object InvitationCodeScreen : OnboardingNavigationEvent()
    data class OnboardingScreen(val clearBackStack: Boolean) : OnboardingNavigationEvent()
    data object SequentialOnboardingScreen : OnboardingNavigationEvent()
    data object ConsentScreen : OnboardingNavigationEvent()
}
