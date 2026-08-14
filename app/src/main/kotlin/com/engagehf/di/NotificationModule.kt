//
// This source file is part of the ENGAGE-HF Android open-source project
//
// SPDX-FileCopyrightText: 2025 Stanford University and the project authors (see CONTRIBUTORS.md)
//
// SPDX-License-Identifier: MIT
//

package com.engagehf.di

import android.content.ComponentName
import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import com.engagehf.MainActivity
import com.engagehf.modules.notification.notifier.Notifications
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NotificationModule {

    @Notifications.TargetActivity
    @Provides
    @Singleton
    fun provideMainActivityComponentName(
        @ApplicationContext context: Context,
    ): ComponentName = ComponentName(context, MainActivity::class.java)
}
