package com.frogsm.instagram_demo.domain.repository

import com.frogsm.instagram_demo.domain.entity.Media
import com.frogsm.instagram_demo.domain.entity.MediaCollection

interface MediaRepository {

    suspend fun getMedia(id: String): Media

    suspend fun getMediaCollection(): MediaCollection
}