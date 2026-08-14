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
    alias(libs.plugins.engagehf.desugaring)
}

android {
    namespace = "com.engagehf.modules.questionnaire"

    buildTypes {
        debug {
            // JaCoCo cannot instrument the HAPI FHIR 6.0.22 jars: mergeExtDex fails with
            // "Execution failed for JacocoTransform". Re-enable once HAPI FHIR is upgraded.
            enableAndroidTestCoverage = false
        }
    }
}

dependencies {
    api(libs.android.fhir.data.capture)
    api(libs.bundles.compose.androidTest)

    implementation(project(":engagehf-modules:core-coroutines"))
    implementation(project(":engagehf-modules:core-logging"))

    api(project(":engagehf-modules:ui"))

    implementation(libs.androidx.fragment.compose)
    androidTestImplementation(project(":engagehf-modules:testing-ui"))
}
