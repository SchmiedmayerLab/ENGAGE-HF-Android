//
// This source file is part of the ENGAGE-HF Android open-source project
//
// SPDX-FileCopyrightText: 2025 Stanford University and the project authors (see CONTRIBUTORS.md)
//
// SPDX-License-Identifier: MIT
//

package com.engagehf.onboarding

import android.content.Context
import com.engagehf.R
import com.engagehf.modules.navigation.Navigator
import com.engagehf.modules.testing.runTestUnconfined
import com.engagehf.modules.utils.MessageNotifier
import com.engagehf.navigation.AppNavigationEvent
import com.google.common.truth.Truth.assertThat
import io.mockk.Runs
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import io.mockk.verify
import org.junit.Before
import org.junit.Test

class EngageConsentManagerTest {
    private val navigator: Navigator = mockk()
    private val messageNotifier: MessageNotifier = mockk()
    private val context: Context = mockk()
    private val manager = EngageConsentManager(
        navigator = navigator,
        messageNotifier = messageNotifier,
        context = context,
    )

    @Before
    fun setup() {
        every { navigator.navigateTo(AppNavigationEvent.AppScreen(true)) } just Runs
        every { messageNotifier.notify(messageId = any(), any()) } just Runs
    }

    @Test
    fun `it should return the correct markdown test`() = runTestUnconfined {
        // given
        val expectedText = """
        # Consent
        The ENGAGE-HF Android Mobile Application will connect to external devices via Bluetooth to record personal health information, including weight, heart rate, and blood pressure.
            
        Your personal information will only be shared with the research team conducting the study.
        """.trimIndent()
        every { context.getString(R.string.consent_markdown_text) } returns expectedText

        // when
        val result = manager.getMarkdownText()

        // then
        assertThat(result).isEqualTo(expectedText)
    }

    @Test
    fun `it should navigate to home screen on consented`() = runTestUnconfined {
        // given
        val navigationEvent = AppNavigationEvent.AppScreen(clearBackStack = true)

        // when
        manager.onConsented()

        // then
        verify { navigator.navigateTo(event = navigationEvent) }
    }

    @Test
    fun `it should notify error message on on consent failure`() = runTestUnconfined {
        // when
        manager.onConsentFailure(error = mockk())

        // then
        verify { messageNotifier.notify(R.string.generic_error_description) }
    }
}
