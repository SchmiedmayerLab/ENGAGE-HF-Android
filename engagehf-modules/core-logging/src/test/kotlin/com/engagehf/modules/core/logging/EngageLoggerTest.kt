//
// This source file is part of the ENGAGE-HF Android open-source project
//
// SPDX-FileCopyrightText: 2025 Stanford University and the project authors (see CONTRIBUTORS.md)
//
// SPDX-License-Identifier: MIT
//

package com.engagehf.modules.core.logging

import com.google.common.truth.Truth.assertThat
import org.junit.Test

class EngageLoggerTest {

    @Test
    fun `GLOBAL_CONFIG must be set to null`() {
        assertThat(EngageLogger.GLOBAL_CONFIG).isNull()
    }
}
