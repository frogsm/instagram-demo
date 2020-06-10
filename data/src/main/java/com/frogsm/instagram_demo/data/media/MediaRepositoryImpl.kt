package com.frogsm.instagram_demo.data.media

import com.frogsm.instagram_demo.data.media.data.MediaData
import com.frogsm.instagram_demo.data.media.data.MediaTypeData
import com.frogsm.instagram_demo.domain.entity.Media
import com.frogsm.instagram_demo.domain.entity.MediaCollection
import com.frogsm.instagram_demo.domain.entity.MediaType
import com.frogsm.instagram_demo.domain.repository.MediaRepository
import javax.inject.Inject

class MediaRepositoryImpl @Inject constructor(
    private val mediaDataSource: MediaDataSource
) : MediaRepository {

    override suspend fun getMedia(id: String): Media {
        val data = mediaDataSource.getMedia(id)
        return data.mapToEntity()
    }

    override suspend fun getMediaCollection(): MediaCollection {
        return mediaDataSource.getMediaCollection().data
            .map { it.mapToEntity() }
            .run { MediaCollection(this) }
    }

    private fun MediaData.mapToEntity(): Media {
        return Media(
            id = id,
            userName = username,
            caption = caption ?: "",
            mediaType = when (media_type) {
                MediaTypeData.IMAGE -> MediaType.IMAGE
                MediaTypeData.VIDEO -> MediaType.VIDEO
                MediaTypeData.ALBUM -> MediaType.ALBUM
            },
            mediaUrl = media_url,
            thumbnailUrl = thumbnail_url,
            timeStamp = timestamp
        )
    }
}