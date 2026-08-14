//
// This source file is part of the ENGAGE-HF Android open-source project
//
// SPDX-FileCopyrightText: 2025 Stanford University and the project authors (see CONTRIBUTORS.md)
//
// SPDX-License-Identifier: MIT
//

package com.engagehf.modules.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

/**
 * An interface for types with the capability to render themselves in compose
 */
interface ComposableContent {
    @Composable
    fun Content(modifier: Modifier)

    @Composable
    fun Content() {
        Content(Modifier)
    }
}
