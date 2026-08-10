//
// This source file is part of the ENGAGE-HF Android open-source project
//
// SPDX-FileCopyrightText: 2025 Stanford University and the project authors (see CONTRIBUTORS.md)
//
// SPDX-License-Identifier: MIT
//

package edu.stanford.bdh.engagehf.modules.notification

import kotlinx.serialization.Serializable

@Serializable
sealed class NotificationRoutes {
    @Serializable
    data object NotificationSetting : NotificationRoutes()
}
