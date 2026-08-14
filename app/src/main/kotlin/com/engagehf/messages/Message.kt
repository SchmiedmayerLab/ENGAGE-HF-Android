//
// This source file is part of the ENGAGE-HF Android open-source project
//
// SPDX-FileCopyrightText: 2025 Stanford University and the project authors (see CONTRIBUTORS.md)
//
// SPDX-License-Identifier: MIT
//

package com.engagehf.messages

import com.google.firebase.firestore.IgnoreExtraProperties
import java.time.ZonedDateTime

@IgnoreExtraProperties
data class Message(
    var id: String,
    val dueDate: ZonedDateTime? = null,
    val completionDate: ZonedDateTime? = null,
    val title: String,
    val description: String? = null,
    val action: MessageAction?,
    val isDismissible: Boolean = true,
)
