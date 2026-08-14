//
// This source file is part of the ENGAGE-HF Android open-source project
//
// SPDX-FileCopyrightText: 2026 Stanford University and the project authors (see CONTRIBUTORS.md)
//
// SPDX-License-Identifier: MIT
//

package com.engagehf.modules.storage.local

import android.content.Context
import com.google.common.truth.Truth.assertThat
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.runTest
import org.junit.Test
import java.io.File
import java.nio.file.Files

class LocalStorageImplTest {
    private val appFilesDir = Files.createTempDirectory("local-storage").toFile()
    private val context: Context = mockk {
        every { filesDir } returns appFilesDir
    }
    private val storage = LocalStorageImpl(
        context = context,
        ioDispatcher = Dispatchers.Unconfined,
        keyStorage = mockk(),
    )

    @Test
    fun `it should store and read values under the current prefix`() = runTest {
        storage.store(KEY, "current value", LocalStorageSetting.Unencrypted) { it.toByteArray() }

        val value = storage.read(KEY, LocalStorageSetting.Unencrypted) { String(it) }

        assertThat(value).isEqualTo("current value")
        assertThat(File(currentDirectory(), "$KEY.localstorage").exists()).isTrue()
    }

    @Test
    fun `it should migrate files installs wrote under the previous prefix`() = runTest {
        val legacyDirectory = File(appFilesDir, "edu.stanford.spezi.storage.LocalStorage")
        legacyDirectory.mkdirs()
        File(legacyDirectory, "$KEY.localstorage").writeBytes("legacy value".toByteArray())

        val value = storage.read(KEY, LocalStorageSetting.Unencrypted) { String(it) }

        assertThat(value).isEqualTo("legacy value")
        assertThat(currentDirectory().exists()).isTrue()
        assertThat(legacyDirectory.exists()).isFalse()
    }

    @Test
    fun `it should not resurrect legacy files once the current prefix exists`() = runTest {
        storage.store(KEY, "current value", LocalStorageSetting.Unencrypted) { it.toByteArray() }
        val legacyDirectory = File(appFilesDir, "edu.stanford.spezi.storage.LocalStorage")
        legacyDirectory.mkdirs()
        File(legacyDirectory, "$KEY.localstorage").writeBytes("legacy value".toByteArray())

        val value = storage.read(KEY, LocalStorageSetting.Unencrypted) { String(it) }

        assertThat(value).isEqualTo("current value")
        assertThat(legacyDirectory.exists()).isTrue()
    }

    private fun currentDirectory() = File(appFilesDir, "com.engagehf.modules.storage.LocalStorage")

    private companion object {
        const val KEY = "some-key"
    }
}
