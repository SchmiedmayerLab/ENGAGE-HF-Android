//
// This source file is part of the ENGAGE-HF Android open-source project
//
// SPDX-FileCopyrightText: 2025 Stanford University and the project authors (see CONTRIBUTORS.md)
//
// SPDX-License-Identifier: MIT
//

package edu.stanford.bdh.engagehf.modules.utils

import android.os.Build
import com.google.common.truth.Truth.assertThat
import org.junit.Test

class BuildInfoTest {
    private val buildInfo = BuildInfoImpl()

    @Test
    fun `it should indicate sdk version`() {
        assertThat(buildInfo.getSdkVersion()).isEqualTo(Build.VERSION.SDK_INT)
    }
}
