//
// This source file is part of the ENGAGE-HF Android open-source project
//
// SPDX-FileCopyrightText: 2025 Stanford University and the project authors (see CONTRIBUTORS.md)
//
// SPDX-License-Identifier: MIT
//

package edu.stanford.spezi.ui.markdown

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import kotlinx.coroutines.delay
import java.nio.charset.StandardCharsets
import kotlin.time.Duration.Companion.milliseconds

@Composable
fun MarkdownTestComposable() {
    Column {
        MarkdownBytes(
            bytes = {
                delay(500.milliseconds)
                "This is a markdown **example** taking half a second to load."
                    .toByteArray(StandardCharsets.UTF_8)
            }
        )

        MarkdownString("This is a markdown **example**.")
    }
}
