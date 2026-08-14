//
// This source file is part of the ENGAGE-HF Android open-source project
//
// SPDX-FileCopyrightText: 2025 Stanford University and the project authors (see CONTRIBUTORS.md)
//
// SPDX-License-Identifier: MIT
//

package com.engagehf.modules.core.internal

import android.content.ContentProvider
import android.content.ContentValues
import android.database.Cursor
import android.net.Uri
import com.engagehf.modules.core.EngageApplication

/**
 * A [ContentProvider] that initializes the [EngageApplication] when the application is created.
 *
 * This is used to ensure that the EngageApplication is configured automatically before any other components
 * in the application.
 */
internal class EngageApplicationContentProvider : ContentProvider() {
    private val logger by engageCoreLogger()

    override fun onCreate(): Boolean {
        logger.i { "Initializing EngageApplicationContentProvider" }
        val application = context?.applicationContext as? EngageApplication
        if (application != null) {
            logger.i { "Engage application available. Configuring Engage" }
            EngageApplication.configure(application = application)
        } else {
            logger.w { "Engage application not available. Skipping configuration for context: ${context?.packageName ?: "null"}" }
        }
        return true
    }

    override fun query(
        uri: Uri,
        projection: Array<out String>?,
        selection: String?,
        selectionArgs: Array<out String>?,
        sortOrder: String?,
    ): Cursor? = null

    override fun getType(uri: Uri): String? = null

    override fun insert(uri: Uri, values: ContentValues?): Uri? = null

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<out String>?): Int = 0

    override fun update(
        uri: Uri,
        values: ContentValues?,
        selection: String?,
        selectionArgs: Array<out String>?,
    ): Int = 0
}
