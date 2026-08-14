//
// This source file is part of the ENGAGE-HF Android open-source project
//
// SPDX-FileCopyrightText: 2025 Stanford University and the project authors (see CONTRIBUTORS.md)
//
// SPDX-License-Identifier: MIT
//

package com.engagehf.simulator

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.ComposeTestRule
import com.engagehf.navigation.screens.AppScreenTestIdentifier
import com.engagehf.modules.testing.ui.onNodeWithIdentifier

class AppSimulator(
    private val composeTestRule: ComposeTestRule,
) {
    private val root = composeTestRule.onNodeWithIdentifier(AppScreenTestIdentifier.ROOT)

    private val topAppBar =
        composeTestRule.onNodeWithIdentifier(AppScreenTestIdentifier.TOP_APP_BAR)

    private val topAppBarTitle =
        composeTestRule.onNodeWithIdentifier(AppScreenTestIdentifier.TOP_APP_BAR_TITLE)

    fun assertIsDisplayed() {
        root.assertIsDisplayed()
    }

    fun assertTopAppBarIsDisplayed() {
        topAppBar.assertIsDisplayed()
    }

    fun assertTopAppBarTitleIsDisplayed(text: String) {
        topAppBarTitle.assertIsDisplayed()
            .assertTextEquals(text)
    }
}
