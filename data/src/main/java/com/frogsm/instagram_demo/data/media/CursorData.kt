package com.frogsm.instagram_demo.data.media

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CursorData(
    val before: String,
    val after: String
)