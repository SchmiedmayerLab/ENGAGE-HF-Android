//
// This source file is part of the ENGAGE-HF Android open-source project
//
// SPDX-FileCopyrightText: 2025 Stanford University and the project authors (see CONTRIBUTORS.md)
//
// SPDX-License-Identifier: MIT
//

package com.engagehf.modules.education

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import com.engagehf.modules.education.videos.data.repository.EducationRepository
import io.mockk.mockk

@Module
@InstallIn(SingletonComponent::class)
class EducationModule {
    private val educationRepository: EducationRepository = mockk(relaxed = true)

    @Provides
    fun provideEducationRepository(): EducationRepository {
        return educationRepository
    }
}
