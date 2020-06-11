package com.frogsm.instagram_demo.data.media.data

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MediaChildrenData(
    val data: List<MediaData>
)