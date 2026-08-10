//
// This source file is part of the ENGAGE-HF Android open-source project
//
// SPDX-FileCopyrightText: 2025 Stanford University and the project authors (see CONTRIBUTORS.md)
//
// SPDX-License-Identifier: MIT
//

package edu.stanford.bdh.engagehf.modules.onboarding.onboarding

/**
 * A sealed class representing the actions that can be performed on the onboarding screen.
 */
sealed interface OnboardingAction {
    data object Continue : OnboardingAction
}

/**
 * The UI state for the onboarding screen.
 */
data class OnboardingUiState(
    val areas: List<Area> = emptyList(),
    val title: String = "Title",
    val subtitle: String = "Subtitle",
    val continueButtonText: String = "Continue",
    val continueAction: () -> Unit = {},
    val error: String? = null,
)
