package com.frogsm.instagram_demo.data.media

import com.frogsm.instagram_demo.data.media.data.MediaCollectionData
import com.frogsm.instagram_demo.data.media.data.MediaData
import com.frogsm.instagram_demo.data.media.data.MediaTypeData
import com.frogsm.instagram_demo.domain.entity.Media
import com.frogsm.instagram_demo.domain.entity.MediaCollection
import com.frogsm.instagram_demo.domain.entity.MediaDetail
import com.frogsm.instagram_demo.domain.entity.MediaType
import com.frogsm.instagram_demo.domain.repository.MediaRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MediaRepositoryImpl @Inject constructor(
    private val mediaLocalDataSource: MediaLocalDataSource,
    private val mediaRemoteDataSource: MediaRemoteDataSource
) : MediaRepository {

    override suspend fun getMedia(id: String): Media {
        val data = mediaRemoteDataSource.getMedia(id)
        return data.mapToEntity()
    }

    override fun getCacheThenFreshMediaDetail(
        id: String
    ): Flow<MediaDetail> = flow {
        val cache = mediaLocalDataSource.getMedia(id)
        if (cache != null) {
            val detailData = cache.mapToEntity()
            emit(MediaDetail(detailData, emptyList<Media>(), true))
        }

        val detailData = mediaRemoteDataSource.getMedia(id)
        val childrenData = mediaRemoteDataSource.getMediaChildren(id)

        val result = MediaDetail(
            media = detailData.mapToEntity(),
            children = childrenData.data
                .map { it.mapToEntity() },
            isCached = false
        )

        emit(result)
    }

    override suspend fun getMediaCollection(): MediaCollection {
        val data = mediaRemoteDataSource.getMediaCollection()
        mediaLocalDataSource.addMedias(data.data)
        return data.mapToEntity()
    }

    override suspend fun getMediaCollection(url: String): MediaCollection {
        val data = mediaRemoteDataSource.getMediaCollection(url)
        mediaLocalDataSource.addMedias(data.data)
        return data.mapToEntity()
    }

    private fun MediaData.mapToEntity(): Media {
        return Media(
            id = id,
            mediaType = when (media_type) {
                MediaTypeData.IMAGE -> MediaType.IMAGE
                MediaTypeData.VIDEO -> MediaType.VIDEO
                MediaTypeData.ALBUM -> MediaType.ALBUM
            },
            mediaUrl = media_url,
            userName = username,
            caption = caption,
            thumbnailUrl = thumbnail_url,
            timeStamp = timestamp
        )
    }

    private fun MediaCollectionData.mapToEntity(): MediaCollection {
        return MediaCollection(
            medias = data.map { it.mapToEntity() },
            nextPageUrl = paging?.next
        )
    }
}