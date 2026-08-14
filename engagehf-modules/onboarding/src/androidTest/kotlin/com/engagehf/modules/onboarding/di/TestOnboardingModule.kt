//
// This source file is part of the ENGAGE-HF Android open-source project
//
// SPDX-FileCopyrightText: 2025 Stanford University and the project authors (see CONTRIBUTORS.md)
//
// SPDX-License-Identifier: MIT
//

package com.engagehf.modules.onboarding.di

import dagger.Module
import dagger.Provides
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import com.engagehf.modules.account.di.AccountModule
import com.engagehf.modules.account.manager.InvitationAuthManager
import com.engagehf.modules.account.manager.UserSessionManager
import com.engagehf.modules.onboarding.consent.ConsentManager
import com.engagehf.modules.onboarding.fakes.FakeOnboardingRepository
import com.engagehf.modules.onboarding.invitation.InvitationCodeRepository
import com.engagehf.modules.onboarding.onboarding.OnboardingRepository
import com.engagehf.modules.onboarding.sequential.SequentialOnboardingRepository
import io.mockk.mockk
import javax.inject.Singleton

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [AccountModule.Bindings::class]
)
class TestOnboardingModule {

    @Provides
    @Singleton
    fun provideInvitationAuthManager(): InvitationAuthManager = mockk()

    @Provides
    @Singleton
    fun provideUserSessionManager(): UserSessionManager = mockk()

    @Provides
    @Singleton
    fun provideOnboardingRepository(
        fakeOnboardingRepository: FakeOnboardingRepository,
    ): OnboardingRepository = fakeOnboardingRepository

    @Provides
    @Singleton
    fun provideInvitationCodeRepository(): InvitationCodeRepository = mockk()

    @Provides
    @Singleton
    fun provideSequentialOnboardingRepository(): SequentialOnboardingRepository = mockk()

    @Provides
    @Singleton
    fun provideOnConsentRepository(): ConsentManager = mockk()
}
