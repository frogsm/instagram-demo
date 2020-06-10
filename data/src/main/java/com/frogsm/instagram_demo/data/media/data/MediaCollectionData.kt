package com.frogsm.instagram_demo.data.media.data

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MediaCollectionData(
    val data: List<MediaData>,
    val paging: PagingData
)