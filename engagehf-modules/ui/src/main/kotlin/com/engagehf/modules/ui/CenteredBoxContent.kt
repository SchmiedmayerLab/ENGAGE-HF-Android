//
// This source file is part of the ENGAGE-HF Android open-source project
//
// SPDX-FileCopyrightText: 2025 Stanford University and the project authors (see CONTRIBUTORS.md)
//
// SPDX-License-Identifier: MIT
//

package com.engagehf.modules.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun CenteredBoxContent(content: @Composable BoxScope.() -> Unit) {
    Box(
        modifier = Modifier
            .testIdentifier(CenteredBoxContentTestIdentifier.ROOT)
            .fillMaxSize(),
        contentAlignment = Alignment.Center,
        content = content,
    )
}

enum class CenteredBoxContentTestIdentifier {
    ROOT,
}
