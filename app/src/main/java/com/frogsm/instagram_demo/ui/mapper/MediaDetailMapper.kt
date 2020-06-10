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
            MediaType.IMAGE,
            MediaType.VIDEO -> listOf(MediaChildrenItem(0, media.mediaUrl)) // media 데이터 사용
            MediaType.ALBUM -> children.mapIndexed { index, child -> // children 데이터 사용
                MediaChildrenItem(index, child.mediaUrl)
            }
        }
    )
}