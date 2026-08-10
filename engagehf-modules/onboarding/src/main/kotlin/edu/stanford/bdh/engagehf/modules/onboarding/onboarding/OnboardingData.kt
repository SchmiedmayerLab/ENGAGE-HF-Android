//
// This source file is part of the ENGAGE-HF Android open-source project
//
// SPDX-FileCopyrightText: 2025 Stanford University and the project authors (see CONTRIBUTORS.md)
//
// SPDX-License-Identifier: MIT
//

package edu.stanford.bdh.engagehf.modules.onboarding.onboarding

data class OnboardingData(
    val areas: List<Area> = emptyList(),
    val title: String = "Title",
    val subTitle: String = "SubTitle",
    val continueButtonText: String = "Learn more",
    val continueButtonAction: () -> Unit = {},
)
