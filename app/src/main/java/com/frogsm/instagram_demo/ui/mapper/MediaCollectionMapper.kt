package com.frogsm.instagram_demo.ui.mapper

import com.frogsm.instagram_demo.domain.entity.MediaCollection
import com.frogsm.instagram_demo.domain.entity.MediaType
import com.frogsm.instagram_demo.ui.mediacollection.list.MediaCollectionItem

fun MediaCollection.mapToMediaCollectionItem(): List<MediaCollectionItem> {
    return data.mapIndexed { index, media ->
        when (media.mediaType) {
            MediaType.IMAGE -> {
                MediaCollectionItem.Image(
                    index = index,
                    thumbnailUrl = media.mediaUrl
                )
            }
            MediaType.VIDEO -> {
                MediaCollectionItem.Image(
                    index = index,
                    thumbnailUrl = media.thumbnailUrl ?: ""
                )
            }

            MediaType.ALBUM -> {
                MediaCollectionItem.Album(
                    index = index,
                    thumbnailUrl = media.mediaUrl
                )
            }
        }
    }
}