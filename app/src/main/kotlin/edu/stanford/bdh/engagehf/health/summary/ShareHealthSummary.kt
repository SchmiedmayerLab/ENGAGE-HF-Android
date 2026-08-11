//
// This source file is part of the ENGAGE-HF Android open-source project
//
// SPDX-FileCopyrightText: 2025 Stanford University and the project authors (see CONTRIBUTORS.md)
//
// SPDX-License-Identifier: MIT
//

package edu.stanford.bdh.engagehf.health.summary

import androidx.compose.ui.graphics.ImageBitmap
import java.time.Instant

data class ShareHealthSummary(
    val qrCodeBitmap: ImageBitmap,
    val oneTimeCode: String,
    val expiresAt: Instant,
)
