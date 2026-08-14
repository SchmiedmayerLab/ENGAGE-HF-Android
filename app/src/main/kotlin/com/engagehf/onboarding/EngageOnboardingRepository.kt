//
// This source file is part of the ENGAGE-HF Android open-source project
//
// SPDX-FileCopyrightText: 2025 Stanford University and the project authors (see CONTRIBUTORS.md)
//
// SPDX-License-Identifier: MIT
//

package com.engagehf.onboarding

import android.content.Context
import com.engagehf.R
import com.engagehf.modules.navigation.Navigator
import com.engagehf.modules.onboarding.OnboardingNavigationEvent
import com.engagehf.modules.onboarding.onboarding.Area
import com.engagehf.modules.onboarding.onboarding.OnboardingData
import com.engagehf.modules.onboarding.onboarding.OnboardingRepository
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import com.engagehf.modules.design.R as DesignR

class EngageOnboardingRepository @Inject constructor(
    private val navigator: Navigator,
    @ApplicationContext private val context: Context,
) : OnboardingRepository {

    override suspend fun getOnboardingData(): Result<OnboardingData> = Result.success(
        OnboardingData(
            areas = listOf(
                Area(
                    title = context.getString(R.string.onboarding_area_1_title),
                    iconId = DesignR.drawable.ic_groups,
                    description = context.getString(R.string.onboarding_area_1_description)
                ),
                Area(
                    title = context.getString(R.string.onboarding_area_2_title),
                    iconId = DesignR.drawable.ic_assignment,
                    description = context.getString(R.string.onboarding_area_2_description)
                ),
                Area(
                    title = context.getString(R.string.onboarding_area_3_title),
                    iconId = DesignR.drawable.ic_vital_signs,
                    description = context.getString(R.string.onboarding_area_3_description)
                )
            ),
            title = context.getString(R.string.onboarding_welcome_to_engage_hf),
            subTitle = context.getString(R.string.onboarding_remote_study_participation_made_easy),
            continueButtonText = context.getString(R.string.onboarding_learn_more),
            continueButtonAction = { navigator.navigateTo(OnboardingNavigationEvent.SequentialOnboardingScreen) }
        )
    )
}
