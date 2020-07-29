package com.frogsm.instagram_demo.data.media

import com.frogsm.instagram_demo.data.api.MediaApi
import com.frogsm.instagram_demo.data.media.data.MediaChildrenData
import com.frogsm.instagram_demo.data.media.data.MediaCollectionData
import com.frogsm.instagram_demo.data.media.data.MediaData
import javax.inject.Inject

class MediaRemoteDataSource @Inject constructor(
    private val mediaApi: MediaApi
) {

    suspend fun getMedia(id: String): MediaData {
        return mediaApi.getMedia(id)
    }

    suspend fun getMediaChildren(id: String): MediaChildrenData {
        return mediaApi.getMediaChildren(id)
    }

    suspend fun getMediaCollection(): MediaCollectionData {
        return mediaApi.getMediaCollection()
    }

    suspend fun getMediaCollection(url: String): MediaCollectionData {
        return mediaApi.getMediaCollectionFromUrl(url)
    }
}