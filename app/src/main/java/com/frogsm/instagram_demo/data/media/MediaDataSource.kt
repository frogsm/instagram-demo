package com.frogsm.instagram_demo.data.media

import com.frogsm.instagram_demo.data.api.MediaApi
import javax.inject.Inject

class MediaDataSource @Inject constructor(
    private val mediaApi: MediaApi
) {

    suspend fun getMediaCollection(): MediaCollectionData {
        return mediaApi.getMediaCollection()
    }
}