//
// This source file is part of the ENGAGE-HF Android open-source project
//
// SPDX-FileCopyrightText: 2025 Stanford University and the project authors (see CONTRIBUTORS.md)
//
// SPDX-License-Identifier: MIT
//

plugins {
    alias(libs.plugins.spezi.library)
    alias(libs.plugins.spezi.compose)
    alias(libs.plugins.spezi.hilt)
    alias(libs.plugins.spezi.desugaring)
}

android {
    namespace = "edu.stanford.spezi.questionnaire"

    buildTypes {
        debug {
            enableAndroidTestCoverage = true
        }
    }
}

dependencies {
    api(libs.android.fhir.data.capture)
    api(libs.bundles.compose.androidTest)

    implementation(project(":spezi:core-coroutines"))
    implementation(project(":spezi:core-logging"))

    api(project(":spezi:ui"))

    implementation(libs.androidx.fragment.compose)
    androidTestImplementation(project(":spezi:testing-ui"))
}
