package com.frogsm.instagram_demo.data.media.data

import com.frogsm.instagram_demo.data.media.data.CursorData
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PagingData(
    val cursors: CursorData
)