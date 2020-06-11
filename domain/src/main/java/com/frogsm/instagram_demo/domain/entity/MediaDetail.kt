package com.frogsm.instagram_demo.domain.entity

data class MediaDetail(
    val media: Media,
    val children: List<Media>
)