//
// This source file is part of the ENGAGE-HF Android open-source project
//
// SPDX-FileCopyrightText: 2025 Stanford University and the project authors (see CONTRIBUTORS.md)
//
// SPDX-License-Identifier: MIT
//

package com.engagehf.modules.storage.local

import java.security.KeyPair

sealed interface LocalStorageSetting {
    data object Unencrypted : LocalStorageSetting
    data class Encrypted(val keyPair: KeyPair) : LocalStorageSetting
    data object EncryptedUsingKeyStore : LocalStorageSetting
}
