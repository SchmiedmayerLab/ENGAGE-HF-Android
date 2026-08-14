//
// This source file is part of the ENGAGE-HF Android open-source project
//
// SPDX-FileCopyrightText: 2025 Stanford University and the project authors (see CONTRIBUTORS.md)
//
// SPDX-License-Identifier: MIT
//

package com.engagehf.modules.onboarding.sequential

/**
 * Represents a step in the onboarding process.
 *
 * @property title The title of the step.
 * @property description The description of the step.
 * @property icon The icon associated with the step.
 * @see com.engagehf.modules.onboarding.sequential.SequentialOnboardingRepository
 * @see com.engagehf.onboarding.EngageSequentialOnboardingRepository
 */
data class Step(
    val title: String,
    val description: String,
    val icon: Int = com.engagehf.modules.design.R.drawable.ic_groups,
)
