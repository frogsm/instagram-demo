package com.frogsm.instagram_demo.domain.entity

data class Media(
    val id: String,
    val mediaType: MediaType,
    val mediaUrl: String,

    val userName: String?,
    val caption: String?,
    val thumbnailUrl: String?,
    val timeStamp: String?
)