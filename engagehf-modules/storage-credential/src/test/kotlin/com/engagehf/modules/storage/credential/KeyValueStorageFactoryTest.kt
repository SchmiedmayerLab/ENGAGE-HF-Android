//
// This source file is part of the ENGAGE-HF Android open-source project
//
// SPDX-FileCopyrightText: 2026 Stanford University and the project authors (see CONTRIBUTORS.md)
//
// SPDX-License-Identifier: MIT
//

package com.engagehf.modules.storage.credential

import android.content.Context
import android.content.SharedPreferences
import com.google.common.truth.Truth.assertThat
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.Test
import java.io.File
import java.nio.file.Files

class KeyValueStorageFactoryTest {
    private val fileName = "${Storage.STORAGE_FILE_PREFIX}${KeyValueStorageType.UNENCRYPTED.name}"
    private val legacyFileName =
        "${Storage.LEGACY_STORAGE_FILE_PREFIX}${KeyValueStorageType.UNENCRYPTED.name}"

    private val appDataDir = Files.createTempDirectory("data").toFile()
    private val legacyPreferences = FakeSharedPreferences().apply {
        edit().putString("token", "legacy").putBoolean("enabled", true).apply()
    }
    private val targetPreferences = FakeSharedPreferences()
    private val context: Context = mockk {
        every { dataDir } returns appDataDir
        every { getSharedPreferences(fileName, Context.MODE_PRIVATE) } returns targetPreferences
        every { getSharedPreferences(legacyFileName, Context.MODE_PRIVATE) } returns legacyPreferences
        every { deleteSharedPreferences(legacyFileName) } returns true
    }
    private val factory = KeyValueStorageFactoryImpl(
        storageFactory = mockk { every { create(any()) } returns mockk() },
        context = context,
    )

    @Test
    fun `it should migrate entries installs stored under the previous file name`() {
        File(appDataDir, "shared_prefs").mkdirs()
        File(File(appDataDir, "shared_prefs"), "$legacyFileName.xml").createNewFile()

        factory.create(fileName, KeyValueStorageType.UNENCRYPTED)

        assertThat(targetPreferences.getString("token", null)).isEqualTo("legacy")
        assertThat(targetPreferences.getBoolean("enabled", false)).isTrue()
        verify { context.deleteSharedPreferences(legacyFileName) }
    }

    @Test
    fun `it should leave fresh installs without a legacy file untouched`() {
        factory.create(fileName, KeyValueStorageType.UNENCRYPTED)

        assertThat(targetPreferences.all).isEmpty()
        verify(exactly = 0) { context.deleteSharedPreferences(any()) }
    }
}

private class FakeSharedPreferences : SharedPreferences {
    private val values = mutableMapOf<String, Any?>()

    override fun getAll(): Map<String, *> = values.toMap()
    override fun getString(key: String, defValue: String?) = values[key] as? String ?: defValue

    @Suppress("UNCHECKED_CAST")
    override fun getStringSet(key: String, defValues: MutableSet<String>?) =
        values[key] as? MutableSet<String> ?: defValues
    override fun getInt(key: String, defValue: Int) = values[key] as? Int ?: defValue
    override fun getLong(key: String, defValue: Long) = values[key] as? Long ?: defValue
    override fun getFloat(key: String, defValue: Float) = values[key] as? Float ?: defValue
    override fun getBoolean(key: String, defValue: Boolean) = values[key] as? Boolean ?: defValue
    override fun contains(key: String) = values.containsKey(key)
    override fun edit(): SharedPreferences.Editor = Editor()
    override fun registerOnSharedPreferenceChangeListener(
        listener: SharedPreferences.OnSharedPreferenceChangeListener?,
    ) = Unit
    override fun unregisterOnSharedPreferenceChangeListener(
        listener: SharedPreferences.OnSharedPreferenceChangeListener?,
    ) = Unit

    private inner class Editor : SharedPreferences.Editor {
        private val pending = mutableMapOf<String, Any?>()
        private var cleared = false

        override fun putString(key: String, value: String?) = apply { pending[key] = value }
        override fun putStringSet(key: String, values: MutableSet<String>?) =
            apply { pending[key] = values }
        override fun putInt(key: String, value: Int) = apply { pending[key] = value }
        override fun putLong(key: String, value: Long) = apply { pending[key] = value }
        override fun putFloat(key: String, value: Float) = apply { pending[key] = value }
        override fun putBoolean(key: String, value: Boolean) = apply { pending[key] = value }
        override fun remove(key: String) = apply { pending[key] = null }
        override fun clear() = apply { cleared = true }

        override fun commit(): Boolean {
            apply()
            return true
        }

        override fun apply() {
            if (cleared) values.clear()
            pending.forEach { (key, value) ->
                if (value == null) values.remove(key) else values[key] = value
            }
        }
    }
}
