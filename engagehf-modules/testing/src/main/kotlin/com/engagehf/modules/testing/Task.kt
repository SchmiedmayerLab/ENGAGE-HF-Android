//
// This source file is part of the ENGAGE-HF Android open-source project
//
// SPDX-FileCopyrightText: 2025 Stanford University and the project authors (see CONTRIBUTORS.md)
//
// SPDX-License-Identifier: MIT
//

package com.engagehf.modules.testing

import com.google.android.gms.tasks.Task
import io.mockk.every
import io.mockk.mockk

/**
 * Returns a mockk task with the given result
 */
fun <T> mockTask(result: T): Task<T> = mockk {
    every { isComplete } returns true
    every { exception } returns null
    every { isCanceled } returns false
    every { this@mockk.result } returns result
}
