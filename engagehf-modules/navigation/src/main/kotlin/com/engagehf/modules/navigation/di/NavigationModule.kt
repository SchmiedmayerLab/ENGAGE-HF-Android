//
// This source file is part of the ENGAGE-HF Android open-source project
//
// SPDX-FileCopyrightText: 2025 Stanford University and the project authors (see CONTRIBUTORS.md)
//
// SPDX-License-Identifier: MIT
//

package com.engagehf.modules.navigation.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import com.engagehf.modules.navigation.Navigator
import com.engagehf.modules.navigation.internal.NavigatorImpl

@Module
@InstallIn(SingletonComponent::class)
abstract class NavigationModule {

    @Binds
    internal abstract fun bindNavigator(impl: NavigatorImpl): Navigator
}
