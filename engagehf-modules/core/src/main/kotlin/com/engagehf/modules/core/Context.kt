//
// This source file is part of the ENGAGE-HF Android open-source project
//
// SPDX-FileCopyrightText: 2025 Stanford University and the project authors (see CONTRIBUTORS.md)
//
// SPDX-License-Identifier: MIT
//

package com.engagehf.modules.core

import android.app.Application
import android.content.Context

/**
 * Returns the application [Context] of the [EngageApplication] if [Application] conforms to [EngageApplication] or null otherwise.
 */
val EngageApplication.applicationContext: Context?
    get() = this as? Application

/**
 * Returns the application [Context] of the [EngageApplication].
 *
 * Note that this method will throw in case the [EngageApplication] is not an instance of [Application].
 */
fun EngageApplication.requireApplicationContext(): Context =
    applicationContext ?: engageError("Only android.app.Application is supported as a EngageApplication")
