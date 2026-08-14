//
// This source file is part of the ENGAGE-HF Android open-source project
//
// SPDX-FileCopyrightText: 2025 Stanford University and the project authors (see CONTRIBUTORS.md)
//
// SPDX-License-Identifier: MIT
//

plugins {
    alias(libs.plugins.engagehf.library)
    alias(libs.plugins.engagehf.compose)
    alias(libs.plugins.engagehf.hilt)
}

android {
    namespace = "com.engagehf.modules.testing.ui"
}

dependencies {
    api(libs.bundles.compose.androidTest)

    api(project(":engagehf-modules:ui"))
    implementation(project(":engagehf-modules:core-logging"))

    implementation(libs.hilt.test)
    implementation(libs.androidx.test.runner)

    api(libs.bundles.unit.testing)
    api(libs.bundles.compose.androidTest)
}
