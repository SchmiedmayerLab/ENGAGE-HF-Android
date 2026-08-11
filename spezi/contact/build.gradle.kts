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
}

android {
    namespace = "edu.stanford.spezi.contact"
}

dependencies {
    implementation(project(":spezi:core-logging"))
    implementation(project(":spezi:foundation"))

    api(project(":spezi:ui-personalinfo"))

    implementation(libs.androidx.core.i18n)
    implementation(libs.androidx.core.ktx)

    androidTestImplementation(libs.hilt.test)
    androidTestImplementation(project(":spezi:testing-ui"))
}
