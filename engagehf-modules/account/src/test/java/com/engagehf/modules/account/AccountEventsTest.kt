//
// This source file is part of the ENGAGE-HF Android open-source project
//
// SPDX-FileCopyrightText: 2025 Stanford University and the project authors (see CONTRIBUTORS.md)
//
// SPDX-License-Identifier: MIT
//

package com.engagehf.modules.account

import com.engagehf.modules.testing.EngageTestScope
import com.engagehf.modules.testing.runTestUnconfined
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.flow.first
import org.junit.Test

class AccountEventsTest {

    private val accountEvents = AccountEvents(scope = EngageTestScope())

    @Test
    fun `given SignInSuccess event when emit is called then events should emit SignInSuccess`() =
        runTestUnconfined {
            // Given
            val event = AccountEvents.Event.SignInSuccess

            // When
            accountEvents.emit(event)

            // Then
            val emittedEvent = accountEvents.events.first()
            assertThat(emittedEvent).isEqualTo(AccountEvents.Event.SignInSuccess)
        }

    @Test
    fun `given SignInFailure event when emit is called then events should emit SignInFailure`() =
        runTestUnconfined {
            // Given
            val event = AccountEvents.Event.SignInFailure

            // When
            accountEvents.emit(event)

            // Then
            val emittedEvent = accountEvents.events.first()
            assertThat(emittedEvent).isEqualTo(AccountEvents.Event.SignInFailure)
        }

    @Test
    fun `given SignUpSuccess event when emit is called then events should emit SignUpSuccess`() =
        runTestUnconfined {
            // Given
            val event = AccountEvents.Event.SignUpSuccess

            // When
            accountEvents.emit(event)

            // Then
            val emittedEvent = accountEvents.events.first()
            assertThat(emittedEvent).isEqualTo(AccountEvents.Event.SignUpSuccess)
        }

    @Test
    fun `given SignUpFailure event when emit is called then events should emit SignUpFailure`() =
        runTestUnconfined {
            // Given
            val event = AccountEvents.Event.SignUpFailure

            // When
            accountEvents.emit(event)

            // Then
            val emittedEvent = accountEvents.events.first()
            assertThat(emittedEvent).isEqualTo(AccountEvents.Event.SignUpFailure)
        }
}
