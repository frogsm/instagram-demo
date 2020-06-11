package com.frogsm.instagram_demo.data.media.data

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PagingData(
    val cursors: CursorData,
    val next: String?,
    val previous: String?
)