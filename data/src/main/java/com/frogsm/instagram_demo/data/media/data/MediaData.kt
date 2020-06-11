package com.frogsm.instagram_demo.data.media.data

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MediaData(
    val id: String,
    val media_type: MediaTypeData,
    val media_url: String,

    val username: String?,
    val caption: String?,
    val thumbnail_url: String?,     // VIDEO 타입 일 때만 내려옴
    val timestamp: String?
)