//
// This source file is part of the ENGAGE-HF Android open-source project
//
// SPDX-FileCopyrightText: 2025 Stanford University and the project authors (see CONTRIBUTORS.md)
//
// SPDX-License-Identifier: MIT
//

package com.engagehf.modules.core

/**
 * A custom exception class used in the Engage framework.
 *
 * @param message The error message to be displayed.
 * @param cause The underlying cause of the error, if any.
 */
class EngageError(message: String, cause: Throwable?) : Throwable(message, cause)

/**
 * A custom error function that throws a [EngageError] with the provided message and optional cause.
 *
 * @param message The error message .
 * @param cause The underlying cause of the error, if any.
 * @return Nothing
 */
fun engageError(message: String, cause: Throwable? = null): Nothing = throw EngageError(message, cause)
