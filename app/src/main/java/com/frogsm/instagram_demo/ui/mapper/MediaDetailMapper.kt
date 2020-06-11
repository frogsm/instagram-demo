package com.frogsm.instagram_demo.ui.mapper

import com.frogsm.instagram_demo.domain.entity.MediaDetail
import com.frogsm.instagram_demo.domain.entity.MediaType
import com.frogsm.instagram_demo.ui.mediadetail.detail.MediaDetailItem
import com.frogsm.instagram_demo.ui.mediadetail.list.MediaChildrenItem
import java.text.SimpleDateFormat
import java.util.*

fun MediaDetail.mapToMediaDetailItem(): MediaDetailItem {
    val summary = media.caption?.lines()?.first() ?: ""
    val extra = media.caption?.removePrefix(summary)?.removePrefix("\n") ?: ""

    val time = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.KOREA)
        .run { format(Date(System.currentTimeMillis())) }

    return MediaDetailItem(
        id = media.id,
        userName = media.userName ?: "",
        summaryCaption = summary,
        extraCaption = extra,
        timeStamp = time,
        children = when (media.mediaType) {
            MediaType.IMAGE -> listOf(MediaChildrenItem.Image(0, media.mediaUrl))
            MediaType.VIDEO -> listOf(MediaChildrenItem.Video(0, media.mediaUrl))
            MediaType.ALBUM -> {
                if (children.isEmpty()) {
                    listOf(MediaChildrenItem.Image(0, media.mediaUrl))
                } else {
                    children.mapIndexed { index, child ->
                        when (child.mediaType) {
                            MediaType.IMAGE -> MediaChildrenItem.Image(index, child.mediaUrl)
                            MediaType.VIDEO -> MediaChildrenItem.Video(index, child.mediaUrl)
                            MediaType.ALBUM -> throw IllegalStateException()
                        }
                    }
                }
            }
        }
    )
}