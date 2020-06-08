package com.frogsm.instagram_demo.data.media

import com.frogsm.instagram_demo.domain.entity.MediaCollection

interface MediaRepository {

    suspend fun getMediaCollection(): MediaCollection
}