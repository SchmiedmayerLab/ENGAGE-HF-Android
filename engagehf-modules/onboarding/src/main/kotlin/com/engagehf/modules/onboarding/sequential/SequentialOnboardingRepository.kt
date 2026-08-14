//
// This source file is part of the ENGAGE-HF Android open-source project
//
// SPDX-FileCopyrightText: 2025 Stanford University and the project authors (see CONTRIBUTORS.md)
//
// SPDX-License-Identifier: MIT
//

package com.engagehf.modules.onboarding.sequential

/**
 * A interface that needs to be implemented and provided by the app to provide a list of steps
 * to be shown in the [com.engagehf.modules.onboarding.sequential.SequentialOnboardingScreen].
 * The implementation should be provided by the app using Dagger.
 * @see com.engagehf.onboarding.EngageSequentialOnboardingRepository
 */
interface SequentialOnboardingRepository {
    suspend fun getSequentialOnboardingData(): SequentialOnboardingData
}
