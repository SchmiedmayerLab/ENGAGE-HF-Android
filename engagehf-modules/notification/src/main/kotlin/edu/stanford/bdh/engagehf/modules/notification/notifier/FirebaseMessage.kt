//
// This source file is part of the ENGAGE-HF Android open-source project
//
// SPDX-FileCopyrightText: 2025 Stanford University and the project authors (see CONTRIBUTORS.md)
//
// SPDX-License-Identifier: MIT
//

package edu.stanford.bdh.engagehf.modules.notification.notifier

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class FirebaseMessage(
    val title: String,
    val message: String,
    val action: String?,
    val messageId: String?,
    val isDismissible: Boolean?,
) : Parcelable {
    companion object {
        const val FIREBASE_MESSAGE_KEY = "FIREBASE_MESSAGE"
    }
}
