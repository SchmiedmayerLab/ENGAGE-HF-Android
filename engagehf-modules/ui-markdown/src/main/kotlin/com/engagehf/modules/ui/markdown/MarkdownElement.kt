//
// This source file is part of the ENGAGE-HF Android open-source project
//
// SPDX-FileCopyrightText: 2025 Stanford University and the project authors (see CONTRIBUTORS.md)
//
// SPDX-License-Identifier: MIT
//

package com.engagehf.modules.ui.markdown

sealed interface MarkdownElement {
    data class Heading(val level: Int, val text: String) : MarkdownElement
    data class Paragraph(val text: String) : MarkdownElement
    data class Bold(val text: String) : MarkdownElement
    data class ListItem(val text: String) : MarkdownElement
}
