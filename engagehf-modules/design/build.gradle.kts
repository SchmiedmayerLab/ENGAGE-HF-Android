//
// This source file is part of the ENGAGE-HF Android open-source project
//
// SPDX-FileCopyrightText: 2025 Stanford University and the project authors (see CONTRIBUTORS.md)
//
// SPDX-License-Identifier: MIT
//

plugins {
    alias(libs.plugins.spezi.library)
    alias(libs.plugins.spezi.hilt)
    alias(libs.plugins.compose.compiler)
}

android {
    namespace = "com.engagehf.modules.design"

    buildFeatures {
        compose = true
    }
}

dependencies {
    implementation(project(":engagehf-modules:utils"))

    implementation(project(":spezi:foundation"))
    implementation(project(":spezi:core"))
    implementation(project(":spezi:ui"))

    val composeBom = platform(libs.compose.bom)
    implementation(composeBom)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.core.ktx)
    implementation(libs.bundles.compose)

    androidTestImplementation(libs.bundles.compose.androidTest)
    androidTestImplementation(composeBom)
    androidTestImplementation(project(":spezi:testing-ui"))

    debugImplementation(libs.compose.ui.tooling)
    debugImplementation(libs.compose.ui.test.manifest)
    implementation(kotlin("reflect"))
}
