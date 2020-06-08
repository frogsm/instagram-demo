package com.frogsm.instagram_demo.data.media

import com.frogsm.instagram_demo.domain.entity.Media
import com.frogsm.instagram_demo.domain.entity.MediaCollection
import com.frogsm.instagram_demo.domain.entity.MediaType
import javax.inject.Inject

class MediaRepositoryImpl @Inject constructor(
    private val mediaDataSource: MediaDataSource
) : MediaRepository {

    override suspend fun getMediaCollection(): MediaCollection {
        return mediaDataSource.getMediaCollection().data
            .map {
                Media(
                    id = it.id,
                    userName = it.username,
                    caption = it.caption,
                    mediaType = when (it.media_type) {
                        MediaTypeData.IMAGE -> MediaType.IMAGE
                        MediaTypeData.VIDEO -> MediaType.VIDEO
                        MediaTypeData.ALBUM -> MediaType.ALBUM
                    },
                    mediaUrl = it.media_url,
                    thumbnailUrl = it.thumbnail_url,
                    timeStamp = it.timestamp
                )
            }
            .run { MediaCollection(this) }
    }
}