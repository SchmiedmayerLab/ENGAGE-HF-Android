//
// This source file is part of the ENGAGE-HF Android open-source project
//
// SPDX-FileCopyrightText: 2025 Stanford University and the project authors (see CONTRIBUTORS.md)
//
// SPDX-License-Identifier: MIT
//

package com.engagehf.modules.core

import com.engagehf.modules.core.internal.Engage

/**
 * Lazy delegate to retrieve an optional module dependency from the [EngageApplication] dependency graph.
 *
 * Example usage:
 *
 * ```kotlin
 *
 * class MyComponent {
 *   val myModule by optionalDependency<MyModule>()
 *
 *   fun doSomething() {
 *      myModule.doSomething()
 *   }
 * }
 */
inline fun <reified M : Module> optionalDependency(identifier: String? = null) = lazy {
    Engage.requireGraph().optionalDependency<M>(identifier)
}

/**
 * Lazy delegate to retrieve a required module dependency from the [EngageApplication] dependency graph.
 *
 * This will throw an exception if the dependency is not found / have been registered beforehand
 * in the [Configuration] block of [EngageApplication].
 *
 * Example usage:
 *
 * ```kotlin
 *
 * class MyComponent {
 *   val myModule by dependency<MyModule>()
 *
 *   fun doSomething() {
 *      myModule.doSomething()
 *   }
 * }
 */
inline fun <reified M : Module> dependency(identifier: String? = null): Lazy<M> = lazy {
    Engage.requireGraph().dependency(identifier)
}
