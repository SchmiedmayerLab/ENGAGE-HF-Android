//
// This source file is part of the ENGAGE-HF Android open-source project
//
// SPDX-FileCopyrightText: 2025 Stanford University and the project authors (see CONTRIBUTORS.md)
//
// SPDX-License-Identifier: MIT
//

package com.engagehf.modules.utils.extensions

import androidx.lifecycle.SavedStateHandle
import kotlinx.serialization.json.Json

inline fun <reified T> SavedStateHandle.decode(key: String): T {
    val jsonString =
        this.get<String>(key) ?: throw IllegalArgumentException("Argument not found")
    return Json.decodeFromString(jsonString)
}
