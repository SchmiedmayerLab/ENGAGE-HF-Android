//
// This source file is part of the ENGAGE-HF Android open-source project
//
// SPDX-FileCopyrightText: 2025 Stanford University and the project authors (see CONTRIBUTORS.md)
//
// SPDX-License-Identifier: MIT
//

package com.engagehf.modules.healthconnectonfhir

import androidx.health.connect.client.records.metadata.Metadata
import java.util.UUID

/**
 * Returns a manual entry metadata with a random uuid as id
 */
@Suppress("FunctionNaming")
fun Metadata(): Metadata = Metadata(id = UUID.randomUUID().toString())

/**
 * Returns a manual entry metadata with the supplied id
 */
@Suppress("FunctionNaming")
fun Metadata(id: String) = Metadata.manualEntryWithId(id = id)
