package com.frogsm.instagram_demo.ui.mapper

import com.frogsm.instagram_demo.domain.entity.MediaCollection
import com.frogsm.instagram_demo.domain.entity.MediaType
import com.frogsm.instagram_demo.ui.mediacollection.list.MediaCollectionItem

fun MediaCollection.mapToMediaCollectionItems(
    lastIndex: Int
): List<MediaCollectionItem> {
    return medias.mapIndexed { index, media ->

        when (media.mediaType) {
            MediaType.IMAGE -> {
                MediaCollectionItem.Image(
                    index = lastIndex + index,
                    mediaId = media.id,
                    thumbnailUrl = media.mediaUrl
                )
            }
            MediaType.VIDEO -> {
                MediaCollectionItem.Video(
                    index = lastIndex + index,
                    mediaId = media.id,
                    thumbnailUrl = media.thumbnailUrl ?: ""
                )
            }

            MediaType.ALBUM -> {
                MediaCollectionItem.Album(
                    index = lastIndex + index,
                    mediaId = media.id,
                    thumbnailUrl = media.mediaUrl
                )
            }
        }
    }
}