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
}

android {
    namespace = "com.engagehf.modules.account"

    buildFeatures {
        buildConfig = true
    }

    packaging {
        resources {
            excludes += "/META-INF/**.md"
        }
    }
}

dependencies {
    implementation(project(":engagehf-modules:design"))
    implementation(project(":engagehf-modules:navigation"))
    implementation(project(":engagehf-modules:utils"))

    implementation(project(":engagehf-modules:foundation"))
    implementation(project(":engagehf-modules:core"))
    implementation(project(":engagehf-modules:core-coroutines"))
    implementation(project(":engagehf-modules:core-logging"))
    implementation(project(":engagehf-modules:ui"))

    implementation(libs.hilt.navigation.compose)

    implementation(libs.firebase.functions.ktx)
    implementation(libs.firebase.auth.ktx)
    implementation(libs.firebase.firestore.ktx)
    implementation(libs.firebase.storage.ktx)

    implementation(libs.androidx.credentials.play.services.auth)
    implementation(libs.play.services.auth)
    implementation(libs.googleid)

    testImplementation(libs.bundles.unit.testing)
    androidTestImplementation(libs.bundles.compose.androidTest)
    androidTestImplementation(project(":engagehf-modules:testing-ui"))
}
