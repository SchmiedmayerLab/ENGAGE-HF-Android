//
// This source file is part of the ENGAGE-HF Android open-source project
//
// SPDX-FileCopyrightText: 2025 Stanford University and the project authors (see CONTRIBUTORS.md)
//
// SPDX-License-Identifier: MIT
//

package com.engagehf.modules.testing.ui

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.annotation.CallSuper
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import dagger.hilt.android.AndroidEntryPoint
import com.engagehf.modules.ui.ComposableBlock
import com.engagehf.modules.ui.theme.EngageTheme
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update

@AndroidEntryPoint
class ComposeContentActivity : AppCompatActivity() {

    private val content = MutableStateFlow<ComposableBlock?>(null)

    @CallSuper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            EngageTheme {
                val content by content.collectAsState()
                content?.invoke()
            }
        }
    }

    fun setScreen(content: ComposableBlock) {
        this.content.update { content }
    }
}
