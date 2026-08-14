//
// This source file is part of the ENGAGE-HF Android open-source project
//
// SPDX-FileCopyrightText: 2025 Stanford University and the project authors (see CONTRIBUTORS.md)
//
// SPDX-License-Identifier: MIT
//

package com.engagehf.modules.notification.di

import android.content.Context
import androidx.core.app.NotificationManagerCompat
import com.engagehf.modules.notification.NotificationPermissions
import com.engagehf.modules.notification.NotificationPermissionsImpl
import com.engagehf.modules.notification.fcm.DeviceRegistrationService
import com.engagehf.modules.notification.fcm.DeviceRegistrationServiceImpl
import com.google.firebase.messaging.FirebaseMessaging
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NotificationModule {

    /**
     * This dependency is provided in a separate module to be replaced for instrumentation tests
     * that run for android versions 31 and 34 where we can't use grant rule
     */
    @Module
    @InstallIn(SingletonComponent::class)
    abstract class NotificationPermissionsBinding {
        @Binds
        internal abstract fun bindNotificationPermissions(
            impl: NotificationPermissionsImpl,
        ): NotificationPermissions
    }

    @Module
    @InstallIn(SingletonComponent::class)
    abstract class Bindings {
        @Binds
        internal abstract fun bindDeviceRegistrationService(
            impl: DeviceRegistrationServiceImpl,
        ): DeviceRegistrationService
    }

    @Provides
    @Singleton
    internal fun provideFirebaseMessaging(): FirebaseMessaging =
        FirebaseMessaging.getInstance()

    @Provides
    fun provideNotificationManagerCompat(@ApplicationContext context: Context): NotificationManagerCompat =
        NotificationManagerCompat.from(context)
}
