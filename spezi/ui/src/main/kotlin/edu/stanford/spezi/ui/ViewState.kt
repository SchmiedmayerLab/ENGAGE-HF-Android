//
// This source file is part of the ENGAGE-HF Android open-source project
//
// SPDX-FileCopyrightText: 2025 Stanford University and the project authors (see CONTRIBUTORS.md)
//
// SPDX-License-Identifier: MIT
//

package edu.stanford.spezi.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.compositionLocalOf

val LocalDefaultErrorTitle = compositionLocalOf { StringResource(R.string.viewstate_default_error_title) }
val LocalDefaultErrorMessage = compositionLocalOf { StringResource(R.string.viewstate_default_error_message) }

sealed interface ViewState {
    data object Idle : ViewState
    data object Processing : ViewState
    data class Error(val throwable: Throwable?) : ViewState {
        val errorTitle: String
            @Composable @ReadOnlyComposable get() = LocalDefaultErrorTitle.current.text()

        val errorMessage: String
            @Composable @ReadOnlyComposable get() = throwable?.localizedMessage ?: LocalDefaultErrorMessage.current.text()
    }
}
