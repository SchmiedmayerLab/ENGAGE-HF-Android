//
// This source file is part of the ENGAGE-HF Android open-source project
//
// SPDX-FileCopyrightText: 2025 Stanford University and the project authors (see CONTRIBUTORS.md)
//
// SPDX-License-Identifier: MIT
//

plugins {
    alias(libs.plugins.engagehf.library)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.engagehf.base)
    alias(libs.plugins.engagehf.hilt)
}

android {
    namespace = "com.engagehf.modules.core.coroutines"
}

dependencies {
    api(libs.bundles.ktx.coroutines)

    api(project(":engagehf-modules:core"))
}
