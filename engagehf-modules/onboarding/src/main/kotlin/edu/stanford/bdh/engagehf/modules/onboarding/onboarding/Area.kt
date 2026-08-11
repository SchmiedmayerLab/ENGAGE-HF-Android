//
// This source file is part of the ENGAGE-HF Android open-source project
//
// SPDX-FileCopyrightText: 2025 Stanford University and the project authors (see CONTRIBUTORS.md)
//
// SPDX-License-Identifier: MIT
//

package edu.stanford.bdh.engagehf.modules.onboarding.onboarding

/**
 * Represents an area of the onboarding screen.
 * @property title The title of the area.
 * @property iconId The resource ID for the icon.
 * @property description The description of the area.
 */
data class Area(
    val title: String,
    val iconId: Int, // Resource ID for the icon
    val description: String,
)
