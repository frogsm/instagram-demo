package com.frogsm.instagram_demo.domain.repository

import com.frogsm.instagram_demo.domain.entity.MediaCollection

interface MediaRepository {

    suspend fun getMediaCollection(): MediaCollection
}