//
// This source file is part of the ENGAGE-HF Android open-source project
//
// SPDX-FileCopyrightText: 2025 Stanford University and the project authors (see CONTRIBUTORS.md)
//
// SPDX-License-Identifier: MIT
//

plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.engagehf.base)
    alias(libs.plugins.engagehf.hilt)
}

android {
    namespace = "com.engagehf.modules.testing"
}

dependencies {
    implementation(project(":engagehf-modules:utils"))

    implementation(project(":engagehf-modules:foundation"))
    implementation(project(":engagehf-modules:core"))
    implementation(project(":engagehf-modules:core-coroutines"))
    implementation(project(":engagehf-modules:ui"))

    implementation(libs.hilt.test)
    implementation(libs.androidx.test.runner)
    implementation(libs.play.services.auth)

    api(libs.bundles.unit.testing)
    api(libs.bundles.compose.androidTest)
}
