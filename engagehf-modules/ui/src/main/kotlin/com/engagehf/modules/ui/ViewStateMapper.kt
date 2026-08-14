//
// This source file is part of the ENGAGE-HF Android open-source project
//
// SPDX-FileCopyrightText: 2025 Stanford University and the project authors (see CONTRIBUTORS.md)
//
// SPDX-License-Identifier: MIT
//

package com.engagehf.modules.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember

@Composable
fun <State : OperationState> mapOperationStateToViewState(state: State): MutableState<ViewState> {
    val result = remember { mutableStateOf(state.representation) }
    LaunchedEffect(state) {
        result.value = state.representation
    }
    return result
}
