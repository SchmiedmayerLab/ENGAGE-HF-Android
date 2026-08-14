//
// This source file is part of the ENGAGE-HF Android open-source project
//
// SPDX-FileCopyrightText: 2025 Stanford University and the project authors (see CONTRIBUTORS.md)
//
// SPDX-License-Identifier: MIT
//

package com.engagehf.modules.onboarding.consent

import androidx.compose.ui.graphics.Path
import com.engagehf.modules.ui.markdown.MarkdownElement

data class ConsentUiState(
    val firstName: FieldState = FieldState(value = "", error = false),
    val lastName: FieldState = FieldState(value = "", error = false),
    val paths: List<Path> = emptyList(),
    val markdownElements: List<MarkdownElement> = emptyList(),
) {
    val isValidForm: Boolean =
        firstName.value.isNotBlank() && lastName.value.isNotBlank() && paths.isNotEmpty()
}

data class FieldState(
    val value: String = "",
    val error: Boolean = false,
)

enum class TextFieldType {
    FIRST_NAME, LAST_NAME
}

sealed interface ConsentAction {
    data class TextFieldUpdate(val newValue: String, val type: TextFieldType) : ConsentAction
    data class AddPath(val path: Path) : ConsentAction
    data object Undo : ConsentAction
    data object Consent : ConsentAction
}
