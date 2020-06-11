package com.frogsm.instagram_demo.domain.repository

import com.frogsm.instagram_demo.domain.entity.Media
import com.frogsm.instagram_demo.domain.entity.MediaCollection
import com.frogsm.instagram_demo.domain.entity.MediaDetail
import kotlinx.coroutines.flow.Flow

interface MediaRepository {

    suspend fun getMedia(id: String): Media

    fun getCacheThenFreshMediaDetail(id: String): Flow<MediaDetail>

    suspend fun getMediaCollection(): MediaCollection

    suspend fun getMediaCollection(url: String): MediaCollection
}