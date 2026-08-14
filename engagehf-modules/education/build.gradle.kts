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
    alias(libs.plugins.engagehf.serialization)
}

android {
    namespace = "com.engagehf.modules.education"
}

dependencies {

    implementation(libs.androidyoutubeplayer.core)
    implementation(libs.hilt.navigation.compose)

    implementation(project(":engagehf-modules:design"))
    implementation(project(":engagehf-modules:navigation"))

    implementation(project(":engagehf-modules:foundation"))
    implementation(project(":engagehf-modules:core"))
    implementation(project(":engagehf-modules:core-logging"))
    implementation(project(":engagehf-modules:ui"))
    androidTestImplementation(project(":engagehf-modules:testing-ui"))
}
