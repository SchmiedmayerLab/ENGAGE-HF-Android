//
// This source file is part of the ENGAGE-HF Android open-source project
//
// SPDX-FileCopyrightText: 2025 Stanford University and the project authors (see CONTRIBUTORS.md)
//
// SPDX-License-Identifier: MIT
//

package com.engagehf.modules.testing

import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlin.coroutines.CoroutineContext

/**
 * Creates a new instance of [TestScope] with the provided [CoroutineContext].
 *
 * This function is useful for creating a [TestScope] with a specific [CoroutineContext], defaulting
 * to [UnconfinedTestDispatcher] if no context is provided.
 *
 * @param context The [CoroutineContext] to be used for the [TestScope]. Defaults to [UnconfinedTestDispatcher].
 * @return A new instance of [TestScope] with the specified [CoroutineContext].
 *
 * Example usage:
 * ```
 * val testScope = EngageTestScope()
 * testScope.launch {
 *     // Coroutine code to test
 * }
 * ```
 */
@Suppress("FunctionNaming")
fun EngageTestScope(context: CoroutineContext = UnconfinedTestDispatcher()): TestScope = TestScope(context = context)
