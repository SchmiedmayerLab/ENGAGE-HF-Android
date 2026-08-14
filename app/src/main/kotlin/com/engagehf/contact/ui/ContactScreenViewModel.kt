//
// This source file is part of the ENGAGE-HF Android open-source project
//
// SPDX-FileCopyrightText: 2025 Stanford University and the project authors (see CONTRIBUTORS.md)
//
// SPDX-License-Identifier: MIT
//

package com.engagehf.contact.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import com.engagehf.R
import com.engagehf.contact.data.EngageContactRepository
import com.engagehf.modules.navigation.NavigationEvent
import com.engagehf.modules.navigation.Navigator
import com.engagehf.modules.contact.Contact
import com.engagehf.modules.core.logging.engageLogger
import com.engagehf.modules.ui.StringResource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class ContactScreenViewModel @Inject constructor(
    private val engageContactRepository: EngageContactRepository,
    private val navigator: Navigator,
) : ViewModel() {
    private val logger by engageLogger()

    private val _uiState = MutableStateFlow<UiState>(UiState.Loading)
    val uiState = _uiState.asStateFlow()

    init {
        loadContact()
    }

    private fun loadContact() {
        viewModelScope.launch {
            engageContactRepository.getContact().fold(onSuccess = { contact ->
                _uiState.value = UiState.ContactLoaded(contact)
            }, onFailure = { error ->
                _uiState.value = UiState.Error(StringResource(R.string.generic_error_description))
                logger.e(error) { "Failed to load contact" }
            })
        }
    }

    fun onAction(action: Action) {
        when (action) {
            Action.Back -> navigator.navigateTo(NavigationEvent.PopBackStack)
        }
    }

    sealed interface Action {
        data object Back : Action
    }

    sealed interface UiState {
        data object Loading : UiState
        data class Error(val message: StringResource) : UiState
        data class ContactLoaded(val contact: Contact) : UiState
    }
}
