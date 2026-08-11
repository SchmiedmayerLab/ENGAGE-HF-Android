//
// This source file is part of the ENGAGE-HF Android open-source project
//
// SPDX-FileCopyrightText: 2025 Stanford University and the project authors (see CONTRIBUTORS.md)
//
// SPDX-License-Identifier: MIT
//

package edu.stanford.bdh.engagehf.modules.utils.extensions

import kotlin.math.pow

@Suppress("MagicNumber")
fun Double.roundToDecimalPlaces(places: Int): Double {
    val factor = (10.0).pow(places)
    return Math.round(this * factor) / factor
}
