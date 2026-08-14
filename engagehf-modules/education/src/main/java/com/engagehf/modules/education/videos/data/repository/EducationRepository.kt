//
// This source file is part of the ENGAGE-HF Android open-source project
//
// SPDX-FileCopyrightText: 2025 Stanford University and the project authors (see CONTRIBUTORS.md)
//
// SPDX-License-Identifier: MIT
//

package com.engagehf.modules.education.videos.data.repository

import com.engagehf.modules.education.videos.Video
import com.engagehf.modules.education.videos.VideoSection

interface EducationRepository {
    suspend fun getVideoSections(): Result<List<VideoSection>>

    suspend fun getVideoBySectionAndVideoId(sectionId: String, videoId: String): Result<Video>
}
