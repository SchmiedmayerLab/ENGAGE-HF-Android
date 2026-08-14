//
// This source file is part of the ENGAGE-HF Android open-source project
//
// SPDX-FileCopyrightText: 2025 Stanford University and the project authors (see CONTRIBUTORS.md)
//
// SPDX-License-Identifier: MIT
//

package com.engagehf.modules.questionnaire

import androidx.compose.ui.test.junit4.createAndroidComposeRule
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import com.engagehf.modules.questionnaire.composables.QuestionnaireTestComposable
import com.engagehf.modules.questionnaire.simulators.QuestionnaireTestSimulator
import com.engagehf.modules.testing.ui.ComposeContentActivity
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
class QuestionnaireTest {

    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComposeContentActivity>()

    @Test
    fun testQuestionnaireComposableDisplay() {
        composeTestRule.activity.setScreen { QuestionnaireTestComposable() }
        questionnaireComposable {
            assertIsDisplayed()
        }
    }

    private fun questionnaireComposable(block: QuestionnaireTestSimulator.() -> Unit) {
        QuestionnaireTestSimulator(composeTestRule).apply { block() }
    }
}
