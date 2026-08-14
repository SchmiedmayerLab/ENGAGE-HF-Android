//
// This source file is part of the ENGAGE-HF Android open-source project
//
// SPDX-FileCopyrightText: 2025 Stanford University and the project authors (see CONTRIBUTORS.md)
//
// SPDX-License-Identifier: MIT
//

package com.engagehf.simulator

import androidx.compose.ui.test.junit4.ComposeTestRule

class OnboardingFlowSimulator(
    composeTestRule: ComposeTestRule,
) {
    private val onboardingScreenSimulator = OnboardingScreenSimulator(composeTestRule)
    private val sequentialOnboardingScreenSimulator = SequentialOnboardingScreenSimulator(composeTestRule)
    private val invitationCodeScreenSimulator = InvitationCodeScreenSimulator(composeTestRule)

    fun onboardingScreen(scope: OnboardingScreenSimulator.() -> Unit) {
        onboardingScreenSimulator.apply(scope)
    }

    fun sequentialOnboarding(scope: SequentialOnboardingScreenSimulator.() -> Unit) {
        sequentialOnboardingScreenSimulator.apply(scope)
    }

    fun invitationCodeScreen(scope: InvitationCodeScreenSimulator.() -> Unit) {
        invitationCodeScreenSimulator.apply(scope)
    }
}
