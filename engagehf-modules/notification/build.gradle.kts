//
// This source file is part of the ENGAGE-HF Android open-source project
//
// SPDX-FileCopyrightText: 2025 Stanford University and the project authors (see CONTRIBUTORS.md)
//
// SPDX-License-Identifier: MIT
//

plugins {
    alias(libs.plugins.engagehf.library)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.engagehf.compose)
    alias(libs.plugins.engagehf.hilt)
    alias(libs.plugins.kotlin.parcelize)
}

android {
    namespace = "com.engagehf.modules.notification"
}

dependencies {
    implementation(libs.firebase.firestore.ktx)
    implementation(libs.firebase.functions.ktx)
    implementation(libs.firebase.messaging.ktx)
    implementation(libs.hilt.navigation.compose)
    implementation(libs.kotlinx.serialization.json)

    implementation(project(":engagehf-modules:account"))
    implementation(project(":engagehf-modules:design"))
    implementation(project(":engagehf-modules:navigation"))

    implementation(project(":engagehf-modules:foundation"))
    implementation(project(":engagehf-modules:core"))
    implementation(project(":engagehf-modules:core-coroutines"))
    implementation(project(":engagehf-modules:core-logging"))
    implementation(project(":engagehf-modules:ui"))
    implementation(project(":engagehf-modules:storage-credential"))
    androidTestImplementation(project(":engagehf-modules:testing-ui"))
}
