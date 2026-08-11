//
// This source file is part of the ENGAGE-HF Android open-source project
//
// SPDX-FileCopyrightText: 2025 Stanford University and the project authors (see CONTRIBUTORS.md)
//
// SPDX-License-Identifier: MIT
//

package edu.stanford.spezi.ui.personalinfo

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType

val KeyboardOptions.Companion.NameDefault
    @Composable @ReadOnlyComposable get() =
        Default.copy(
            capitalization = KeyboardCapitalization.Words,
            keyboardType = KeyboardType.Text
        )
