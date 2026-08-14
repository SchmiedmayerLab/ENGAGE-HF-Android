//
// This source file is part of the ENGAGE-HF Android open-source project
//
// SPDX-FileCopyrightText: 2025 Stanford University and the project authors (see CONTRIBUTORS.md)
//
// SPDX-License-Identifier: MIT
//

package com.engagehf.buildlogic.convention.model

enum class PluginId(val id: String) {
    ANDROID_APPLICATION(id = "com.android.application"),
    ANDROID_LIBRARY(id = "com.android.library"),
    JETBRAINS_KOTLIN_ANDROID(id = "org.jetbrains.kotlin.android"),
    HILT(id = "com.google.dagger.hilt.android"),
    KSP(id = "com.google.devtools.ksp"),
    COMPOSE_COMPILER("org.jetbrains.kotlin.plugin.compose"),
    SERIALIZATION("org.jetbrains.kotlin.plugin.serialization"),
}
