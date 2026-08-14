//
// This source file is part of the ENGAGE-HF Android open-source project
//
// SPDX-FileCopyrightText: 2025 Stanford University and the project authors (see CONTRIBUTORS.md)
//
// SPDX-License-Identifier: MIT
//

package com.engagehf.modules.testing.ui

import androidx.compose.ui.test.SemanticsMatcher
import androidx.compose.ui.test.SemanticsNodeInteractionsProvider
import androidx.compose.ui.test.onAllNodesWithTag
import androidx.compose.ui.test.onNodeWithTag
import com.engagehf.modules.ui.SemanticKeys
import com.engagehf.modules.ui.TestIdentifier
import com.engagehf.modules.ui.tag

fun SemanticsNodeInteractionsProvider.onNodeWithIdentifier(
    identifier: TestIdentifier,
    suffix: String? = null,
    useUnmergedTree: Boolean = false,
) = onNodeWithTag(identifier.tag(suffix = suffix), useUnmergedTree = useUnmergedTree)

fun SemanticsNodeInteractionsProvider.onNodeWithContent(content: String) = onNode(
    matcher = SemanticsMatcher.expectValue(SemanticKeys.Content, content),
    useUnmergedTree = true,
)

fun SemanticsNodeInteractionsProvider.onAllNodes(
    identifier: TestIdentifier,
    useUnmergedTree: Boolean = false,
) = onAllNodesWithTag(identifier.tag(), useUnmergedTree)

fun SemanticsNodeInteractionsProvider.waitNode(
    identifier: TestIdentifier,
) = onAllNodesWithTag(identifier.tag()).fetchSemanticsNodes().isNotEmpty()
