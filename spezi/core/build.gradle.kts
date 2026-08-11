//
// This source file is part of the ENGAGE-HF Android open-source project
//
// SPDX-FileCopyrightText: 2025 Stanford University and the project authors (see CONTRIBUTORS.md)
//
// SPDX-License-Identifier: MIT
//

plugins {
    alias(libs.plugins.spezi.library)
}

android {
    namespace = "edu.stanford.spezi.core"
}

dependencies {
    implementation(project(":spezi:core-logging"))
    implementation(libs.kotlin.reflect)
    api(project(":spezi:foundation"))
}
