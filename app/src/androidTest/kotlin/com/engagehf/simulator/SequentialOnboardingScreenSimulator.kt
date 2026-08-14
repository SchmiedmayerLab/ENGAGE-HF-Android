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
import androidx.compose.ui.test.performClick
import com.engagehf.modules.onboarding.sequential.SequentialOnboardingScreenTestIdentifier
import com.engagehf.modules.onboarding.sequential.components.PageIndicatorTestIdentifier
import com.engagehf.modules.testing.ui.onNodeWithIdentifier
import com.engagehf.modules.testing.ui.waitNode

class SequentialOnboardingScreenSimulator(
    private val composeTestRule: ComposeTestRule,
) {
    private val root =
        composeTestRule.onNodeWithIdentifier(SequentialOnboardingScreenTestIdentifier.ROOT)
    private val pager =
        composeTestRule.onNodeWithIdentifier(SequentialOnboardingScreenTestIdentifier.PAGER)
    private val pageIndicator =
        composeTestRule.onNodeWithIdentifier(SequentialOnboardingScreenTestIdentifier.PAGE_INDICATOR)
    private val forwardButton = composeTestRule.onNodeWithIdentifier(PageIndicatorTestIdentifier.FORWARD)

    fun assertIsDisplayed() {
        composeTestRule.waitNode(SequentialOnboardingScreenTestIdentifier.ROOT)
        root.assertIsDisplayed()
    }

    fun assertPagerIsDisplayed() {
        pager.assertIsDisplayed()
    }

    fun assertPageIndicatorIsDisplayed() {
        pageIndicator.assertIsDisplayed()
    }

    fun assertPageTitle(text: String) {
        composeTestRule
            .onNodeWithIdentifier(SequentialOnboardingScreenTestIdentifier.PAGE, text)
            .assertIsDisplayed()
    }

    fun clickForward() {
        forwardButton.performClick()
    }
}
