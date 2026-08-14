//
// This source file is part of the ENGAGE-HF Android open-source project
//
// SPDX-FileCopyrightText: 2025 Stanford University and the project authors (see CONTRIBUTORS.md)
//
// SPDX-License-Identifier: MIT
//

package com.engagehf.modules.ui

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.engagehf.modules.ui.theme.Colors

@OptIn(ExperimentalMaterial3Api::class)
interface BottomSheetComposableContent : ComposableContent {
    val skipPartiallyExpanded: Boolean get() = false
    val dragHandle: ComposableBlock? get() = null
    val onDismiss: () -> Unit

    @Composable
    fun Sheet(modifier: Modifier) {
        ModalBottomSheet(
            modifier = modifier,
            sheetState = rememberSheetState(),
            onDismissRequest = { onDismiss() },
            containerColor = Colors.surface,
            dragHandle = dragHandle,
        ) { Content() }
    }

    @Composable
    fun rememberSheetState() = rememberModalBottomSheetState(skipPartiallyExpanded = skipPartiallyExpanded)
}
