package com.frogsm.instagram_demo.domain.entity

data class MediaCollection(
    val medias: List<Media>,
    val nextPageUrl: String?
)