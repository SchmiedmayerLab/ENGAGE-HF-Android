//
// This source file is part of the ENGAGE-HF Android open-source project
//
// SPDX-FileCopyrightText: 2025 Stanford University and the project authors (see CONTRIBUTORS.md)
//
// SPDX-License-Identifier: MIT
//

package edu.stanford.bdh.engagehf.education

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import edu.stanford.bdh.engagehf.modules.education.videos.data.repository.EducationRepository

@Module
@InstallIn(SingletonComponent::class)
abstract class EducationModule {
    @Binds
    abstract fun bindEducationRepository(
        engageEducationRepository: EngageEducationRepository,
    ): EducationRepository
}
