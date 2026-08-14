//
// This source file is part of the ENGAGE-HF Android open-source project
//
// SPDX-FileCopyrightText: 2025 Stanford University and the project authors (see CONTRIBUTORS.md)
//
// SPDX-License-Identifier: MIT
//

package com.engagehf.modules.onboarding.fakes

import com.engagehf.modules.design.R
import com.engagehf.modules.onboarding.onboarding.Area
import com.engagehf.modules.onboarding.onboarding.OnboardingData
import com.engagehf.modules.onboarding.onboarding.OnboardingRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FakeOnboardingRepository @Inject constructor() : OnboardingRepository {
    private var onContinueAction: (() -> Unit)? = null

    override suspend fun getOnboardingData(): Result<OnboardingData> {
        return Result.success(
            OnboardingData(
                title = "Onboarding screen",
                subTitle = "Onboarding screen subtitle",
                continueButtonText = "Learn more",
                continueButtonAction = onContinueAction ?: {},
                areas = listOf(
                    Area(
                        title = "Area 1",
                        iconId = R.drawable.ic_vital_signs,
                        description = "Area 1 description"
                    )
                )
            )
        )
    }

    fun setOnContinueAction(action: () -> Unit) {
        onContinueAction = action
    }
}
