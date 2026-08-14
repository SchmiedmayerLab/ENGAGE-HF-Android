//
// This source file is part of the ENGAGE-HF Android open-source project
//
// SPDX-FileCopyrightText: 2025 Stanford University and the project authors (see CONTRIBUTORS.md)
//
// SPDX-License-Identifier: MIT
//

package com.engagehf.simulator

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.ComposeTestRule
import com.engagehf.modules.account.AccountNavigationEvent
import com.engagehf.modules.account.login.LoginScreenTestIdentifier
import com.engagehf.modules.account.register.RegisterScreenTestIdentifier
import com.engagehf.modules.navigation.Navigator
import com.engagehf.modules.onboarding.OnboardingNavigationEvent
import com.engagehf.modules.onboarding.invitation.InvitationCodeScreenTestIdentifier
import com.engagehf.modules.onboarding.onboarding.OnboardingScreenTestIdentifier
import com.engagehf.modules.onboarding.sequential.SequentialOnboardingScreenTestIdentifier
import com.engagehf.navigation.AppNavigationEvent
import com.engagehf.navigation.screens.AppScreenTestIdentifier
import edu.stanford.spezi.testing.ui.onAllNodes
import edu.stanford.spezi.testing.ui.onNodeWithIdentifier
import edu.stanford.spezi.ui.TestIdentifier

class NavigatorSimulator(
    private val composeTestRule: ComposeTestRule,
    private val navigator: Navigator,
) {
    private val onboarding =
        composeTestRule.onNodeWithIdentifier(OnboardingScreenTestIdentifier.ROOT)
    private val register = composeTestRule.onNodeWithIdentifier(RegisterScreenTestIdentifier.ROOT)
    private val login = composeTestRule.onNodeWithIdentifier(LoginScreenTestIdentifier.ROOT)
    private val invitation =
        composeTestRule.onNodeWithIdentifier(InvitationCodeScreenTestIdentifier.ROOT)
    private val sequential =
        composeTestRule.onNodeWithIdentifier(SequentialOnboardingScreenTestIdentifier.ROOT)
    private val appScreen = composeTestRule.onNodeWithIdentifier(AppScreenTestIdentifier.ROOT)

    fun assertOnboardingIsDisplayed() {
        waitNode(OnboardingScreenTestIdentifier.ROOT)
        onboarding.assertIsDisplayed()
    }

    fun assertAppScreenIsDisplayed() {
        waitNode(AppScreenTestIdentifier.ROOT)
        appScreen.assertIsDisplayed()
    }

    fun assertLoginScreenIsDisplayed() {
        waitNode(LoginScreenTestIdentifier.ROOT)
        login.assertIsDisplayed()
    }

    fun assertRegisterScreenIsDisplayed() {
        waitNode(RegisterScreenTestIdentifier.ROOT)
        register.assertIsDisplayed()
    }

    fun assertInvitationCodeScreenIsDisplayed() {
        waitNode(InvitationCodeScreenTestIdentifier.ROOT)
        invitation.assertIsDisplayed()
    }

    fun assertSequentialOnboardingScreenIsDisplayed() {
        waitNode(SequentialOnboardingScreenTestIdentifier.ROOT)
        sequential.assertIsDisplayed()
    }

    fun navigateToAppScreen() {
        navigator.navigateTo(AppNavigationEvent.AppScreen(false))
    }

    fun navigateToOnboardingScreen() {
        navigator.navigateTo(OnboardingNavigationEvent.OnboardingScreen(false))
    }

    fun navigateToLoginScreen() {
        navigator.navigateTo(AccountNavigationEvent.LoginScreen)
    }

    fun navigateToRegisterScreen() {
        navigator.navigateTo(AccountNavigationEvent.RegisterScreen())
    }

    fun navigateToInvitationCodeScreen() {
        navigator.navigateTo(OnboardingNavigationEvent.InvitationCodeScreen)
    }

    fun navigateToSequentialOnboardingScreen() {
        navigator.navigateTo(OnboardingNavigationEvent.SequentialOnboardingScreen)
    }

    private fun waitNode(testIdentifier: TestIdentifier) {
        composeTestRule.waitUntil {
            composeTestRule.onAllNodes(testIdentifier).fetchSemanticsNodes().isNotEmpty()
        }
    }
}
