//
// This source file is part of the ENGAGE-HF Android open-source project
//
// SPDX-FileCopyrightText: 2025 Stanford University and the project authors (see CONTRIBUTORS.md)
//
// SPDX-License-Identifier: MIT
//

package com.engagehf.modules.storage.credential

import javax.inject.Qualifier

interface Storage {

    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class Encrypted

    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class Unencrypted

    companion object {
        internal const val STORAGE_FILE_PREFIX = "edu.stanford.spezi.storage."
    }
}
