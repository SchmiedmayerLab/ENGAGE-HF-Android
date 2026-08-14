//
// This source file is part of the ENGAGE-HF Android open-source project
//
// SPDX-FileCopyrightText: 2025 Stanford University and the project authors (see CONTRIBUTORS.md)
//
// SPDX-License-Identifier: MIT
//

package com.engagehf.modules.healthconnectonfhir

import androidx.health.connect.client.records.Record
import com.google.firebase.firestore.DocumentSnapshot
import org.hl7.fhir.r4.model.Observation

interface ObservationsDocumentMapper {
    fun <T : Record> map(observationDocument: DocumentSnapshot): T

    fun map(observation: Observation): Map<String, Any>
}
