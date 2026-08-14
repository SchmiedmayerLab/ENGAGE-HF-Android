//
// This source file is part of the ENGAGE-HF Android open-source project
//
// SPDX-FileCopyrightText: 2025 Stanford University and the project authors (see CONTRIBUTORS.md)
//
// SPDX-License-Identifier: MIT
//

package com.engagehf.bluetooth.service

import com.engagehf.modules.foundation.UUID
import java.util.UUID

/**
 * Enumeration of Bluetooth Low Energy (BLE) service types.
 */
enum class BLEServiceType(
    /**
     * The UUID of the BLE service.
     */
    val service: UUID,

    /**
     * The UUID of the characteristic associated with the BLE service.
     */
    val characteristic: UUID,
) {
    /**
     * Represents the Weight service.
     */
    WEIGHT(
        service = UUID("0000181d-0000-1000-8000-00805f9b34fb"),
        characteristic = UUID("00002a9d-0000-1000-8000-00805f9b34fb")
    ),

    /**
     * Represents the Blood Pressure service.
     */
    BLOOD_PRESSURE(
        service = UUID("00001810-0000-1000-8000-00805f9b34fb"),
        characteristic = UUID("00002a35-0000-1000-8000-00805f9b34fb")
    ),
}
