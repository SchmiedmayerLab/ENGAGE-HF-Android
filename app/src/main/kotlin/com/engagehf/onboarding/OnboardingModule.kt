//
// This source file is part of the ENGAGE-HF Android open-source project
//
// SPDX-FileCopyrightText: 2025 Stanford University and the project authors (see CONTRIBUTORS.md)
//
// SPDX-License-Identifier: MIT
//

package com.engagehf.onboarding

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import com.engagehf.modules.onboarding.consent.ConsentManager
import com.engagehf.modules.onboarding.invitation.InvitationCodeRepository
import com.engagehf.modules.onboarding.onboarding.OnboardingRepository
import com.engagehf.modules.onboarding.sequential.SequentialOnboardingRepository

/**
 *  A Dagger module that provides dependencies for the onboarding feature.
 */
@Module
@InstallIn(SingletonComponent::class)
abstract class OnboardingModule {

    @Binds
    abstract fun bindOnboardingRepository(
        engageOnboardingRepository: EngageOnboardingRepository,
    ): OnboardingRepository

    @Binds
    abstract fun bindInvitationCodeRepository(
        engageInvitationCodeRepository: EngageInvitationCodeRepository,
    ): InvitationCodeRepository

    @Binds
    abstract fun bindSequentialOnboardingRepository(
        engageSequentialOnboardingRepository: EngageSequentialOnboardingRepository,
    ): SequentialOnboardingRepository

    @Binds
    abstract fun bindOnConsentRepository(
        engageConsentManager: EngageConsentManager,
    ): ConsentManager
}
