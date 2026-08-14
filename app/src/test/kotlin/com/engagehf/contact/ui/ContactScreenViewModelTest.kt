//
// This source file is part of the ENGAGE-HF Android open-source project
//
// SPDX-FileCopyrightText: 2025 Stanford University and the project authors (see CONTRIBUTORS.md)
//
// SPDX-License-Identifier: MIT
//

package com.engagehf.contact.ui

import com.google.common.truth.Truth.assertThat
import com.engagehf.R
import com.engagehf.contact.data.EngageContactRepository
import com.engagehf.contact.ui.ContactScreenViewModel.UiState
import com.engagehf.modules.navigation.NavigationEvent
import com.engagehf.modules.navigation.Navigator
import com.engagehf.modules.testing.CoroutineTestRule
import com.engagehf.modules.contact.Contact
import com.engagehf.modules.ui.StringResource
import io.mockk.coEvery
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class ContactScreenViewModelTest {

    @get:Rule
    val coroutineTestRule = CoroutineTestRule()

    private val engageContactRepository: EngageContactRepository = mockk(relaxed = true)
    private val navigator: Navigator = mockk(relaxed = true)

    private val viewModel by lazy {
        ContactScreenViewModel(
            engageContactRepository = engageContactRepository,
            navigator = navigator,
        )
    }

    @Before
    fun setup() {
        coEvery { engageContactRepository.getContact() } returns Result.success(mockk(relaxed = true))
    }

    @Test
    fun `loadContact should update state on success`() = runTest {
        // Given
        val contact = mockk<Contact>()
        coEvery { engageContactRepository.getContact() } returns Result.success(contact)

        // When

        // Then
        val uiState = viewModel.uiState.first()
        assertThat(uiState).isInstanceOf(UiState.ContactLoaded::class.java)
        val loadedState = uiState as UiState.ContactLoaded
        assertThat(loadedState.contact).isEqualTo(contact)
    }

    @Test
    fun `loadContact should update state on failure`() = runTest {
        // Given
        coEvery { engageContactRepository.getContact() } returns Result.failure(Exception("Failed to load contact"))

        // When

        // Then
        val uiState = viewModel.uiState.first()
        assertThat(uiState).isEqualTo(UiState.Error(StringResource(R.string.generic_error_description)))
    }

    @Test
    fun `onAction Back should navigate back`() {
        // When
        viewModel.onAction(ContactScreenViewModel.Action.Back)

        // Then
        verify { navigator.navigateTo(NavigationEvent.PopBackStack) }
    }
}
