//
// This source file is part of the ENGAGE-HF Android open-source project
//
// SPDX-FileCopyrightText: 2025 Stanford University and the project authors (see CONTRIBUTORS.md)
//
// SPDX-License-Identifier: MIT
//

plugins {
    alias(libs.plugins.spezi.application)
    alias(libs.plugins.spezi.compose)
    alias(libs.plugins.spezi.hilt)
    alias(libs.plugins.spezi.desugaring)
    alias(libs.plugins.spezi.serialization)
    alias(libs.plugins.kotlin.parcelize)
    alias(libs.plugins.google.gms.google.services)
}

android {
    namespace = "com.engagehf"
    compileSdk = libs.versions.compileSdk.get().toInt()

    defaultConfig {
        applicationId = (project.findProperty("android.injected.application.id") as? String) ?: "edu.stanford.bdh.engagehf"
        versionCode =
            (project.findProperty("android.injected.version.code") as? String)?.toInt() ?: 1
        versionName =
            (project.findProperty("android.injected.version.name") as? String)
                ?: providers.gradleProperty("app.versionName").get()
        targetSdk = libs.versions.targetSdk.get().toInt()

        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildFeatures {
        buildConfig = true
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            buildConfigField("boolean", "USE_FIREBASE_EMULATOR", "false")
        }
        debug {
            // JaCoCo cannot instrument the HAPI FHIR 6.0.22 jars: mergeExtDex fails with
            // "Execution failed for JacocoTransform". Re-enable once HAPI FHIR is upgraded.
            enableAndroidTestCoverage = false
            buildConfigField("boolean", "USE_FIREBASE_EMULATOR", "true")
        }
    }
}

dependencies {
    implementation(project(":engagehf-modules:account"))
    implementation(project(":engagehf-modules:bluetooth"))
    implementation(project(":engagehf-modules:design"))
    implementation(project(":engagehf-modules:education"))
    implementation(project(":engagehf-modules:healthconnectonfhir"))
    implementation(project(":engagehf-modules:navigation"))
    implementation(project(":engagehf-modules:notification"))
    implementation(project(":engagehf-modules:onboarding"))

    implementation(project(":spezi:contact"))
    implementation(project(":spezi:foundation"))
    implementation(project(":spezi:core"))
    implementation(project(":spezi:core-coroutines"))
    implementation(project(":spezi:core-logging"))
    implementation(project(":spezi:ui"))

    implementation(project(":spezi:ui-personalinfo"))
    implementation(project(":spezi:questionnaire"))

    implementation(libs.firebase.firestore.ktx)
    implementation(libs.firebase.functions.ktx)

    implementation(libs.androidx.core.i18n)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.fragment.compose)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.lifecycle.view.model.ktx)
    implementation(libs.androidx.splashscreen)

    implementation(libs.hilt.navigation.compose)
    implementation(libs.navigation.compose)
    implementation(libs.vico.compose.m3)

    implementation(libs.zxing.core)

    implementation(libs.googlecode.phonenumber)

    androidTestImplementation(project(":engagehf-modules:testing"))
    implementation(project(":spezi:testing-ui"))
    androidTestImplementation(project(":spezi:testing-ui"))
}
