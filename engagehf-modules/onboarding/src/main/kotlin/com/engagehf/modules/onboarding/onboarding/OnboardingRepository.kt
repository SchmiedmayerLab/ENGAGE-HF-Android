//
// This source file is part of the ENGAGE-HF Android open-source project
//
// SPDX-FileCopyrightText: 2025 Stanford University and the project authors (see CONTRIBUTORS.md)
//
// SPDX-License-Identifier: MIT
//

package com.engagehf.modules.onboarding.onboarding

/**
 * Repository for fetching onboarding data.
 * @see com.engagehf.onboarding.EngageOnboardingRepository
 */
interface OnboardingRepository {

    /**
     * Fetches the areas of the onboarding screen.
     * @return A list of [com.engagehf.modules.onboarding.onboarding.Area] objects.
     * @see com.engagehf.modules.onboarding.onboarding.Area
     */
    suspend fun getOnboardingData(): Result<OnboardingData>
}
