//
// This source file is part of the ENGAGE-HF Android open-source project
//
// SPDX-FileCopyrightText: 2025 Stanford University and the project authors (see CONTRIBUTORS.md)
//
// SPDX-License-Identifier: MIT
//

package com.engagehf.education

import com.google.firebase.firestore.FirebaseFirestore
import com.engagehf.modules.education.videos.Video
import com.engagehf.modules.education.videos.VideoSection
import com.engagehf.modules.education.videos.data.repository.EducationRepository
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class EngageEducationRepository @Inject constructor(
    private val firebaseFirestore: FirebaseFirestore,
    private val mapper: VideoSectionDocumentToVideoSectionMapper,
) : EducationRepository {

    override suspend fun getVideoSections(): Result<List<VideoSection>> {
        return runCatching {
            firebaseFirestore
                .collection(SECTIONS_PATH)
                .get()
                .await()
                .documents
                .mapNotNull { document -> mapper.map(document) }
                .sortedBy { it.orderIndex }
        }
    }

    override suspend fun getVideoBySectionAndVideoId(
        sectionId: String,
        videoId: String,
    ): Result<Video> {
        return runCatching {
            val document = firebaseFirestore.collection(SECTIONS_PATH)
                .document(sectionId)
                .collection(VIDEO_PATH)
                .document(videoId)
                .get()
                .await()
            mapper.mapVideo(document = document) ?: error("Video not found")
        }
    }

    private companion object {
        const val SECTIONS_PATH = "videoSections"
        const val VIDEO_PATH = "videos"
    }
}
