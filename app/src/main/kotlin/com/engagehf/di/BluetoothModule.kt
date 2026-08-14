//
// This source file is part of the ENGAGE-HF Android open-source project
//
// SPDX-FileCopyrightText: 2025 Stanford University and the project authors (see CONTRIBUTORS.md)
//
// SPDX-License-Identifier: MIT
//

package com.engagehf.di

import com.engagehf.bluetooth.service.mapper.MeasurementMapper
import com.engagehf.bluetooth.service.mapper.MeasurementMapperImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class BluetoothModule {
    /**
     * Binds the implementation of [MeasurementMapper] interface.
     *
     * @param impl The implementation of [MeasurementMapper].
     * @return An instance of [MeasurementMapper].
     */
    @Binds
    internal abstract fun bindMeasurementMapper(impl: MeasurementMapperImpl): MeasurementMapper
}
