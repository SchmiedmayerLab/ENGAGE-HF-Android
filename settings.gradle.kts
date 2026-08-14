//
// This source file is part of the ENGAGE-HF Android open-source project
//
// SPDX-FileCopyrightText: 2025 Stanford University and the project authors (see CONTRIBUTORS.md)
//
// SPDX-License-Identifier: MIT
//

pluginManagement {
    includeBuild("build-logic")

    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    @Suppress("UnstableApiUsage")
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    @Suppress("UnstableApiUsage")
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "ENGAGE-HF-Android"

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

// Please keep the projects sorted. Select all method calls below and in Android Studio `Edit > Sort Lines`
include(":app")
include(":engagehf-modules:account")
include(":engagehf-modules:bluetooth")
include(":engagehf-modules:design")
include(":engagehf-modules:education")
include(":engagehf-modules:healthconnectonfhir")
include(":engagehf-modules:navigation")
include(":engagehf-modules:notification")
include(":engagehf-modules:onboarding")
include(":engagehf-modules:testing")
include(":engagehf-modules:utils")
include("engagehf-modules:contact")
include("engagehf-modules:core")
include("engagehf-modules:core-coroutines")
include("engagehf-modules:core-logging")
include("engagehf-modules:core-testing")
include("engagehf-modules:foundation")
include("engagehf-modules:questionnaire")
include("engagehf-modules:storage-credential")
include("engagehf-modules:storage-local")
include("engagehf-modules:testing-ui")
include("engagehf-modules:ui")
include("engagehf-modules:ui-markdown")
include("engagehf-modules:ui-personalinfo")
include("engagehf-modules:ui-theme")
