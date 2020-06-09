package com.frogsm.instagram_demo.data.media

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MediaChildrenData(
    val id: String,
    val media_type: MediaTypeData,
    val media_url: String
)