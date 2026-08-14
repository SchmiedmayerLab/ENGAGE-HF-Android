//
// This source file is part of the ENGAGE-HF Android open-source project
//
// SPDX-FileCopyrightText: 2025 Stanford University and the project authors (see CONTRIBUTORS.md)
//
// SPDX-License-Identifier: MIT
//

package com.engagehf.modules.notification.notifier

import javax.inject.Qualifier

interface Notifications {
    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class TargetActivity
}
