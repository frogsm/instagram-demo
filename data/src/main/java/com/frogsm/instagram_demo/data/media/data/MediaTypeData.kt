package com.frogsm.instagram_demo.data.media.data

import com.squareup.moshi.FromJson

enum class MediaTypeData(val type: String) {
    IMAGE("IMAGE"),
    VIDEO("VIDEO"),
    ALBUM("CAROUSEL_ALBUM");

    class MoshiAdapter {
        @FromJson
        fun fromJson(json: String): MediaTypeData = when (json) {
            "IMAGE" -> IMAGE
            "VIDEO" -> VIDEO
            "CAROUSEL_ALBUM" -> ALBUM
            else -> throw IllegalArgumentException()
        }
    }
}