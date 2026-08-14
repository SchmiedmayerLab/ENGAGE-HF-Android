//
// This source file is part of the ENGAGE-HF Android open-source project
//
// SPDX-FileCopyrightText: 2025 Stanford University and the project authors (see CONTRIBUTORS.md)
//
// SPDX-License-Identifier: MIT
//

package com.engagehf.modules.storage.credential

import android.content.Context
import android.content.SharedPreferences
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import com.engagehf.modules.core.logging.engageLogger
import dagger.hilt.android.qualifiers.ApplicationContext
import java.io.File
import javax.inject.Inject
import javax.inject.Singleton

interface KeyValueStorageFactory {
    fun create(
        fileName: String,
        type: KeyValueStorageType,
    ): KeyValueStorage
}

@Singleton
internal class KeyValueStorageFactoryImpl @Inject constructor(
    private val storageFactory: KeyValueStorageImpl.Factory,
    @ApplicationContext private val context: Context,
) : KeyValueStorageFactory {
    private val logger by engageLogger()

    private val masterKey: MasterKey by lazy {
        MasterKey.Builder(context)
            .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
            .build()
    }

    override fun create(fileName: String, type: KeyValueStorageType): KeyValueStorage {
        val preferences by createSharedPreferences(fileName = fileName, type = type)
        return storageFactory.create(preferences)
    }

    private fun createSharedPreferences(
        fileName: String,
        type: KeyValueStorageType,
    ): Lazy<SharedPreferences> {
        return lazy(LazyThreadSafetyMode.SYNCHRONIZED) {
            when (type) {
                KeyValueStorageType.UNENCRYPTED -> createUnencryptedStorage(fileName = fileName)

                KeyValueStorageType.ENCRYPTED -> createEncryptedStorage(fileName = fileName).getOrNull() ?: run {
                    logger.w { "First encrypted storage creation failed, deleting existing file and retrying..." }
                    context.deleteSharedPreferences(fileName)
                    createEncryptedStorage(fileName = fileName).getOrThrow()
                }
            }.also { migrateLegacyStorage(fileName = fileName, type = type, target = it) }
        }
    }

    // Installs from before the com.engagehf rename keep their entries under the old file name.
    private fun migrateLegacyStorage(
        fileName: String,
        type: KeyValueStorageType,
        target: SharedPreferences,
    ) = runCatching {
        if (!fileName.startsWith(Storage.STORAGE_FILE_PREFIX)) return@runCatching
        val legacyFileName =
            Storage.LEGACY_STORAGE_FILE_PREFIX + fileName.removePrefix(Storage.STORAGE_FILE_PREFIX)
        val legacyFile = File(File(context.dataDir, "shared_prefs"), "$legacyFileName.xml")
        if (!legacyFile.exists()) return@runCatching
        val legacy = when (type) {
            KeyValueStorageType.UNENCRYPTED -> createUnencryptedStorage(fileName = legacyFileName)
            KeyValueStorageType.ENCRYPTED -> createEncryptedStorage(fileName = legacyFileName).getOrThrow()
        }
        val editor = target.edit()
        legacy.all.forEach { (key, value) ->
            when (value) {
                is Boolean -> editor.putBoolean(key, value)
                is Float -> editor.putFloat(key, value)
                is Int -> editor.putInt(key, value)
                is Long -> editor.putLong(key, value)
                is String -> editor.putString(key, value)
                is Set<*> -> @Suppress("UNCHECKED_CAST") editor.putStringSet(key, value as Set<String>)
            }
        }
        editor.apply()
        context.deleteSharedPreferences(legacyFileName)
        logger.i { "Migrated ${legacy.all.size} entries from $legacyFileName" }
    }.onFailure {
        logger.e(it) { "Failed to migrate legacy storage for $fileName" }
    }

    private fun createUnencryptedStorage(fileName: String) = context.getSharedPreferences(
        fileName,
        Context.MODE_PRIVATE
    )

    private fun createEncryptedStorage(fileName: String) = runCatching {
        EncryptedSharedPreferences.create(
            context,
            fileName,
            masterKey,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )
    }.onSuccess {
        logger.i { "Successfully created encrypted storage $fileName" }
    }
}
