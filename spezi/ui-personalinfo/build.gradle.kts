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
    namespace = "edu.stanford.spezi.ui.personalinfo"
}

dependencies {
    implementation(project(":spezi:core-logging"))

    api(project(":spezi:ui"))
    androidTestImplementation(project(":spezi:testing-ui"))
}
