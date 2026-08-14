//
// This source file is part of the ENGAGE-HF Android open-source project
//
// SPDX-FileCopyrightText: 2025 Stanford University and the project authors (see CONTRIBUTORS.md)
//
// SPDX-License-Identifier: MIT
//

package com.engagehf.bluetooth.component

import androidx.compose.ui.test.junit4.createComposeRule
import com.engagehf.simulator.DoNewMeasurementBottomSheetSimulator
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class DoNewMeasurementBottomSheetKtTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Before
    fun init() {
        composeTestRule.setContent {
            DoNewMeasurementBottomSheet()
        }
    }

    @Test
    fun `it should display the title correctly`() {
        doNewMeasurementBottomSheet {
            assertTitleIsDisplayed()
        }
    }

    @Test
    fun `it should display the progress bar correctly`() {
        doNewMeasurementBottomSheet {
            assertProgressBarIsDisplayed()
        }
    }

    @Test
    fun `it should display the description correctly`() {
        doNewMeasurementBottomSheet {
            assertDescriptionIsDisplayed()
        }
    }

    @Test
    fun `it should display the blood pressure icon correctly`() {
        doNewMeasurementBottomSheet {
            assertBloodPressureIconIsDisplayed()
        }
    }

    @Test
    fun `it should display the weight icon correctly`() {
        doNewMeasurementBottomSheet {
            assertWeightIconIsDisplayed()
        }
    }

    private fun doNewMeasurementBottomSheet(block: DoNewMeasurementBottomSheetSimulator.() -> Unit) {
        DoNewMeasurementBottomSheetSimulator(composeTestRule).apply(block)
    }
}
