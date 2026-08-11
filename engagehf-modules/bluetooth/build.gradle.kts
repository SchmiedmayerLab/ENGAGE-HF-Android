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
    alias(libs.plugins.spezi.serialization)
}

android {
    namespace = "edu.stanford.bdh.engagehf.modules.bluetooth"
}

dependencies {
    implementation(project(":engagehf-modules:utils"))

    implementation(project(":spezi:foundation"))
    implementation(project(":spezi:core"))
    implementation(project(":spezi:core-coroutines"))
    implementation(project(":spezi:core-logging"))
    implementation(project(":spezi:storage-credential"))
}
