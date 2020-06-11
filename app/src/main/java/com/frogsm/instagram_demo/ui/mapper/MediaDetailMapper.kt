package com.frogsm.instagram_demo.ui.mapper

import com.frogsm.instagram_demo.domain.entity.MediaDetail
import com.frogsm.instagram_demo.domain.entity.MediaType
import com.frogsm.instagram_demo.ui.mediadetail.detail.MediaDetailItem
import com.frogsm.instagram_demo.ui.mediadetail.list.MediaChildrenItem

fun MediaDetail.mapToMediaDetailItem(): MediaDetailItem {
    return MediaDetailItem(
        id = media.id,
        userName = media.userName ?: "",
        caption = media.caption ?: "",
        timeStamp = media.timeStamp ?: "",
        children = when (media.mediaType) {
            MediaType.IMAGE -> listOf(MediaChildrenItem.Image(0, media.mediaUrl))
            MediaType.VIDEO -> listOf(MediaChildrenItem.Video(0, media.mediaUrl))
            MediaType.ALBUM -> children.mapIndexed { index, child ->
                when (child.mediaType) {
                    MediaType.IMAGE -> MediaChildrenItem.Image(index, child.mediaUrl)
                    MediaType.VIDEO -> MediaChildrenItem.Video(index, child.mediaUrl)
                    MediaType.ALBUM -> throw IllegalStateException()
                }
            }
        }
    )
}