//
// This source file is part of the ENGAGE-HF Android open-source project
//
// SPDX-FileCopyrightText: 2025 Stanford University and the project authors (see CONTRIBUTORS.md)
//
// SPDX-License-Identifier: MIT
//

package com.engagehf.modules.account.di

import android.content.Context
import androidx.credentials.CredentialManager
import com.engagehf.modules.account.BuildConfig
import com.engagehf.modules.account.manager.FirebaseInvitationAuthManager
import com.engagehf.modules.account.manager.InvitationAuthManager
import com.engagehf.modules.account.manager.UserSessionManager
import com.engagehf.modules.account.manager.UserSessionManagerImpl
import com.google.android.gms.auth.api.identity.Identity
import com.google.android.gms.auth.api.identity.SignInClient
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.functions.FirebaseFunctions
import com.google.firebase.storage.FirebaseStorage
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AccountModule {
    private val userFirebaseEmulator by lazy { BuildConfig.DEBUG }

    @Singleton
    @Provides
    fun provideSignInClient(@ApplicationContext context: Context): SignInClient {
        return Identity.getSignInClient(context)
    }

    @Singleton
    @Provides
    fun provideCredentialManager(@ApplicationContext context: Context): CredentialManager {
        return CredentialManager.create(context)
    }

    @Provides
    @Singleton
    internal fun provideFirebaseFunctions(): FirebaseFunctions =
        FirebaseFunctions.getInstance().apply {
            if (userFirebaseEmulator) {
                useEmulator(FIREBASE_EMULATOR_HOST, FIREBASE_FUNCTIONS_EMULATOR_PORT)
            }
        }

    @Provides
    @Singleton
    internal fun provideFirebaseAuth(): FirebaseAuth = FirebaseAuth.getInstance().apply {
        if (userFirebaseEmulator) {
            useEmulator(FIREBASE_EMULATOR_HOST, FIREBASE_AUTH_EMULATOR_PORT)
        }
    }

    @Provides
    @Singleton
    internal fun provideFirebaseFirestore(): FirebaseFirestore =
        FirebaseFirestore.getInstance().apply {
            if (userFirebaseEmulator) {
                useEmulator(FIREBASE_EMULATOR_HOST, FIREBASE_FIRESTORE_EMULATOR_PORT)
            }
        }

    @Provides
    @Singleton
    internal fun provideFirebaseStorage(): FirebaseStorage = FirebaseStorage.getInstance().apply {
        if (userFirebaseEmulator) {
            useEmulator(FIREBASE_EMULATOR_HOST, FIREBASE_STORAGE_EMULATOR_PORT)
        }
    }

    @Module
    @InstallIn(SingletonComponent::class)
    abstract class Bindings {
        @Binds
        internal abstract fun bindInvitationAuthManager(
            firebaseInvitationAuthManager: FirebaseInvitationAuthManager,
        ): InvitationAuthManager

        @Binds
        internal abstract fun bindUserSessionManager(
            impl: UserSessionManagerImpl,
        ): UserSessionManager
    }

    private companion object {
        const val FIREBASE_EMULATOR_HOST = "10.0.2.2"
        const val FIREBASE_FUNCTIONS_EMULATOR_PORT = 5001
        const val FIREBASE_AUTH_EMULATOR_PORT = 9099
        const val FIREBASE_FIRESTORE_EMULATOR_PORT = 8080
        const val FIREBASE_STORAGE_EMULATOR_PORT = 9199
    }
}
