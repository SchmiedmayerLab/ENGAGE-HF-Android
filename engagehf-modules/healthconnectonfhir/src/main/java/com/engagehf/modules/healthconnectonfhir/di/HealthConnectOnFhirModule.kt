//
// This source file is part of the ENGAGE-HF Android open-source project
//
// SPDX-FileCopyrightText: 2025 Stanford University and the project authors (see CONTRIBUTORS.md)
//
// SPDX-License-Identifier: MIT
//

package com.engagehf.modules.healthconnectonfhir.di

import ca.uhn.fhir.context.FhirContext
import ca.uhn.fhir.context.FhirVersionEnum
import ca.uhn.fhir.parser.IParser
import com.engagehf.modules.healthconnectonfhir.ObservationsDocumentMapper
import com.engagehf.modules.healthconnectonfhir.QuestionnaireDocumentMapper
import com.engagehf.modules.healthconnectonfhir.RecordToObservationMapper
import com.engagehf.modules.healthconnectonfhir.internal.ObservationsDocumentMapperImpl
import com.engagehf.modules.healthconnectonfhir.internal.QuestionnaireDocumentMapperImpl
import com.engagehf.modules.healthconnectonfhir.internal.RecordToObservationMapperImpl
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class HealthConnectOnFhirModule {

    @Provides
    internal fun provideRecordToObservationMapper(
        impl: RecordToObservationMapperImpl,
    ): RecordToObservationMapper = impl

    @Provides
    internal fun provideObservationsDocumentMapper(
        impl: ObservationsDocumentMapperImpl,
    ): ObservationsDocumentMapper = impl

    @Provides
    internal fun provideQuestionnaireDocumentMapper(
        impl: QuestionnaireDocumentMapperImpl,
    ): QuestionnaireDocumentMapper = impl

    @Provides
    @Singleton
    internal fun provideGson() = Gson()

    @Provides
    @Singleton
    internal fun provideIParser(): IParser = FhirContext.forCached(FhirVersionEnum.R4).newJsonParser()
}
