//
// This source file is part of the ENGAGE-HF Android open-source project
//
// SPDX-FileCopyrightText: 2025 Stanford University and the project authors (see CONTRIBUTORS.md)
//
// SPDX-License-Identifier: MIT
//

plugins {
    alias(libs.plugins.engagehf.library)
    alias(libs.plugins.engagehf.hilt)
    alias(libs.plugins.engagehf.serialization)
}

android {
    namespace = "com.engagehf.modules.storage.credential"
}

dependencies {
    api(project(":engagehf-modules:core"))
    implementation(project(":engagehf-modules:core-logging"))

    implementation(libs.androidx.security.crypto.ktx)

    androidTestImplementation(libs.hilt.test)
    androidTestImplementation(project(":engagehf-modules:testing-ui"))
}
