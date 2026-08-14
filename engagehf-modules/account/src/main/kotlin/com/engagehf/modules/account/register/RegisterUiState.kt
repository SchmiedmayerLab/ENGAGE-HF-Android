//
// This source file is part of the ENGAGE-HF Android open-source project
//
// SPDX-FileCopyrightText: 2025 Stanford University and the project authors (see CONTRIBUTORS.md)
//
// SPDX-License-Identifier: MIT
//

package com.engagehf.modules.account.register

data class RegisterUiState(
    val email: FieldState = FieldState(),
    val password: FieldState = FieldState(),
    val isFormValid: Boolean = false,
    val isPasswordVisible: Boolean = false,
    val isRegisterButtonEnabled: Boolean = false,
    val isProcessing: Boolean = false,
)

data class FieldState(
    val value: String = "",
    val error: String? = null,
)

enum class TextFieldType {
    EMAIL,
    PASSWORD,
}

sealed interface Action {
    data class TextFieldUpdate(val newValue: String, val type: TextFieldType) : Action
    data object OnRegisterPressed : Action
    data object TogglePasswordVisibility : Action
}
