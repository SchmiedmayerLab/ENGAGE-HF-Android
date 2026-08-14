//
// This source file is part of the ENGAGE-HF Android open-source project
//
// SPDX-FileCopyrightText: 2025 Stanford University and the project authors (see CONTRIBUTORS.md)
//
// SPDX-License-Identifier: MIT
//

package com.engagehf.modules.education.videos

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.ComposeTestRule
import edu.stanford.spezi.testing.ui.onNodeWithIdentifier

class EducationScreenSimulator(composeTestRule: ComposeTestRule) {

    private val videoSection =
        composeTestRule.onNodeWithIdentifier(EducationScreenTestIdentifier.VIDEO_SECTION)

    private val retryButton =
        composeTestRule.onNodeWithIdentifier(EducationScreenTestIdentifier.RETRY_BUTTON)

    private val loadingRoot =
        composeTestRule.onNodeWithIdentifier(EducationScreenTestIdentifier.LOADING_ROOT)

    fun assertLoading() = loadingRoot.assertIsDisplayed()

    fun assertVideoSection() = videoSection.assertIsDisplayed()

    fun assertRetryButton() = retryButton.assertIsDisplayed()
}
