//
// This source file is part of the ENGAGE-HF Android open-source project
//
// SPDX-FileCopyrightText: 2025 Stanford University and the project authors (see CONTRIBUTORS.md)
//
// SPDX-License-Identifier: MIT
//

package com.engagehf.modules.ui.markdown

import androidx.compose.ui.test.junit4.createComposeRule
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class MarkdownTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Before
    fun init() {
        composeTestRule.setContent {
            MarkdownTestComposable()
        }
    }

    @Test
    fun testMarkdown() {
        markdown {
            waitForTextToAppear(
                "This is a markdown example.",
                timeoutMillis = 100
            )
            assertTextExists(
                "This is a markdown example taking half a second to load.",
                exists = false
            )
            waitForTextToAppear(
                "This is a markdown example taking half a second to load.",
            )
        }
    }

    private fun markdown(block: MarkdownTestSimulator.() -> Unit) {
        MarkdownTestSimulator(composeTestRule).apply(block)
    }
}
