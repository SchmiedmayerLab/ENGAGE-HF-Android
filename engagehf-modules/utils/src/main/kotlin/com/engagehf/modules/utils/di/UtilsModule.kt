//
// This source file is part of the ENGAGE-HF Android open-source project
//
// SPDX-FileCopyrightText: 2025 Stanford University and the project authors (see CONTRIBUTORS.md)
//
// SPDX-License-Identifier: MIT
//

package com.engagehf.modules.utils.di

import com.engagehf.modules.utils.BuildInfo
import com.engagehf.modules.utils.BuildInfoImpl
import com.engagehf.modules.utils.LocaleProvider
import com.engagehf.modules.utils.LocaleProviderImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class UtilsModule {

    @Binds
    internal abstract fun bindLocaleProvider(impl: LocaleProviderImpl): LocaleProvider

    @Binds
    internal abstract fun bindBuildInfo(impl: BuildInfoImpl): BuildInfo
}
