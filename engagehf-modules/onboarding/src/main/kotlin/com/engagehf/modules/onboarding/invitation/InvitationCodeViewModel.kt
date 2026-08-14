//
// This source file is part of the ENGAGE-HF Android open-source project
//
// SPDX-FileCopyrightText: 2025 Stanford University and the project authors (see CONTRIBUTORS.md)
//
// SPDX-License-Identifier: MIT
//

package com.engagehf.modules.onboarding.invitation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import com.engagehf.modules.account.manager.InvitationAuthManager
import com.engagehf.modules.onboarding.R
import edu.stanford.spezi.ui.StringResource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class InvitationCodeViewModel @Inject internal constructor(
    private val invitationAuthManager: InvitationAuthManager,
    invitationCodeRepository: InvitationCodeRepository,
) : ViewModel() {
    private val screenData = invitationCodeRepository.getScreenData()
    private val _uiState =
        MutableStateFlow(
            InvitationCodeUiState(
                description = screenData.description,
                error = null,
            )
        )
    val uiState = _uiState.asStateFlow()

    fun onAction(action: Action) {
        when (action) {
            is Action.UpdateInvitationCode -> {
                _uiState.update {
                    it.copy(invitationCode = action.invitationCode)
                }
            }

            is Action.ClearError -> {
                _uiState.update {
                    it.copy(error = null)
                }
            }

            Action.RedeemInvitationCode -> {
                redeemInvitationCode()
            }
        }
    }

    private fun redeemInvitationCode() {
        viewModelScope.launch {
            val result = invitationAuthManager.checkInvitationCode(uiState.value.invitationCode)
            if (result.isSuccess) {
                screenData.redeemAction()
            } else {
                _uiState.update {
                    it.copy(error = StringResource(R.string.onboarding_invitation_code_error_message))
                }
            }
        }
    }
}
