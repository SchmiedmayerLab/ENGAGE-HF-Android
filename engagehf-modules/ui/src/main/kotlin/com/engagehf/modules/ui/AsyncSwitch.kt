//
// This source file is part of the ENGAGE-HF Android open-source project
//
// SPDX-FileCopyrightText: 2025 Stanford University and the project authors (see CONTRIBUTORS.md)
//
// SPDX-License-Identifier: MIT
//

package com.engagehf.modules.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Switch
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.engagehf.modules.ui.theme.Colors
import com.engagehf.modules.ui.theme.Sizes
import com.engagehf.modules.ui.theme.EngageTheme
import com.engagehf.modules.ui.theme.ThemePreviews

@Composable
fun AsyncSwitch(
    modifier: Modifier = Modifier,
    isLoading: Boolean = false,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    enabled: Boolean = isLoading.not(),
) {
    Box(
        modifier = modifier.height(Sizes.Icon.medium)
    ) {
        if (isLoading) {
            Box(
                modifier = Modifier.fillMaxHeight(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(
                    color = Colors.primary,
                )
            }
        } else {
            Switch(
                checked = checked,
                onCheckedChange = onCheckedChange,
                enabled = enabled,
                modifier = Modifier.align(Alignment.Center)
            )
        }
    }
}

private class AsyncSwitchPreviewParameterProvider : PreviewParameterProvider<AsyncSwitchState> {
    override val values = sequenceOf(
        AsyncSwitchState(isLoading = true, checked = false),
        AsyncSwitchState(isLoading = false, checked = true),
        AsyncSwitchState(isLoading = false, checked = false)
    )
}

private data class AsyncSwitchState(
    val isLoading: Boolean,
    val checked: Boolean,
)

@ThemePreviews
@Composable
private fun AsyncSwitchPreview(
    @PreviewParameter(AsyncSwitchPreviewParameterProvider::class) state: AsyncSwitchState,
) {
    EngageTheme {
        AsyncSwitch(
            isLoading = state.isLoading,
            checked = state.checked,
            onCheckedChange = {},
        )
    }
}
