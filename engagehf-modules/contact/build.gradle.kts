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
}

android {
    namespace = "com.engagehf.modules.contact"
}

dependencies {
    implementation(project(":engagehf-modules:core-logging"))
    implementation(project(":engagehf-modules:foundation"))

    api(project(":engagehf-modules:ui-personalinfo"))

    implementation(libs.androidx.core.i18n)
    implementation(libs.androidx.core.ktx)

    androidTestImplementation(libs.hilt.test)
    androidTestImplementation(project(":engagehf-modules:testing-ui"))
}
