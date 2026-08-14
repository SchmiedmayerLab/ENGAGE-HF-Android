//
// This source file is part of the ENGAGE-HF Android open-source project
//
// SPDX-FileCopyrightText: 2025 Stanford University and the project authors (see CONTRIBUTORS.md)
//
// SPDX-License-Identifier: MIT
//

package com.engagehf.bluetooth.screen

import android.Manifest
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.test.rule.GrantPermissionRule
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import com.engagehf.R
import com.engagehf.bluetooth.data.models.UiState
import com.engagehf.simulator.HomeScreenSimulator
import com.engagehf.modules.testing.ui.ComposeContentActivity
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
class HomeScreenTest {

    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComposeContentActivity>()

    @get:Rule
    val runtimePermissionRule: GrantPermissionRule = GrantPermissionRule.grant(
        Manifest.permission.BLUETOOTH_CONNECT,
        Manifest.permission.BLUETOOTH_SCAN,
        Manifest.permission.ACCESS_COARSE_LOCATION,
        Manifest.permission.ACCESS_FINE_LOCATION,
    )

    @Before
    fun init() {
        composeTestRule.activity.setScreen {
            HomeScreen()
        }
    }

    @Test
    fun `test home screen root is displayed`() {
        homeScreen {
            assertIsDisplayed()
        }
    }

    @Test
    fun `test home screen message title is displayed`() {
        homeScreen {
            assertMessageTitle(composeTestRule.activity.getString(R.string.messages))
        }
    }

    @Test
    fun `test home screen vital title is displayed`() {
        homeScreen {
            assertVitalTitle(composeTestRule.activity.getString(R.string.vitals))
        }
    }

    @Test
    fun `test home screen vital is displayed`() {
        homeScreen {
            val uiState = UiState()
            assertVital(uiState.weight.title.get(composeTestRule.activity))
            assertVital(uiState.heartRate.title.get(composeTestRule.activity))
            assertVital(uiState.bloodPressure.title.get(composeTestRule.activity))
        }
    }

    private fun homeScreen(block: HomeScreenSimulator.() -> Unit) {
        HomeScreenSimulator(composeTestRule).apply(block)
    }
}
