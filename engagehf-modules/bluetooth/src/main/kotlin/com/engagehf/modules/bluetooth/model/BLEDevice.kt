//
// This source file is part of the ENGAGE-HF Android open-source project
//
// SPDX-FileCopyrightText: 2025 Stanford University and the project authors (see CONTRIBUTORS.md)
//
// SPDX-License-Identifier: MIT
//

package com.engagehf.modules.bluetooth.model

import kotlinx.serialization.Serializable

@Serializable
data class BLEDevice(
    val address: String,
    val name: String,
    val connected: Boolean,
    val lastSeenTimeStamp: Long = 0L,
)
