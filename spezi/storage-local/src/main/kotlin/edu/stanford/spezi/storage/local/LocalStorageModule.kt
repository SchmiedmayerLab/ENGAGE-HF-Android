//
// This source file is part of the ENGAGE-HF Android open-source project
//
// SPDX-FileCopyrightText: 2025 Stanford University and the project authors (see CONTRIBUTORS.md)
//
// SPDX-License-Identifier: MIT
//

package edu.stanford.spezi.storage.local

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class LocalStorageModule {

    @Module
    @InstallIn(SingletonComponent::class)
    abstract class Bindings {
        @Binds
        internal abstract fun bindKeyStorage(
            impl: KeyStorageImpl,
        ): KeyStorage

        @Binds
        internal abstract fun bindLocalStorage(
            impl: LocalStorageImpl,
        ): LocalStorage
    }
}
