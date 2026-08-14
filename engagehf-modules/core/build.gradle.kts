//
// This source file is part of the ENGAGE-HF Android open-source project
//
// SPDX-FileCopyrightText: 2025 Stanford University and the project authors (see CONTRIBUTORS.md)
//
// SPDX-License-Identifier: MIT
//

plugins {
    alias(libs.plugins.engagehf.library)
}

android {
    namespace = "com.engagehf.modules.core"
}

dependencies {
    implementation(project(":engagehf-modules:core-logging"))
    implementation(libs.kotlin.reflect)
    api(project(":engagehf-modules:foundation"))
}
