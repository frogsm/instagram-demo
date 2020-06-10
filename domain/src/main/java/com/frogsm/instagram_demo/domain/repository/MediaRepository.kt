package com.frogsm.instagram_demo.domain.repository

import com.frogsm.instagram_demo.domain.entity.Media
import com.frogsm.instagram_demo.domain.entity.MediaCollection
import com.frogsm.instagram_demo.domain.entity.MediaDetail

interface MediaRepository {

    suspend fun getMedia(id: String): Media

    suspend fun getMediaDetail(id: String): MediaDetail

    suspend fun getMediaCollection(): MediaCollection
}