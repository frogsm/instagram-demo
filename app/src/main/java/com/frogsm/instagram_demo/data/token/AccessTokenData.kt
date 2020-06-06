package com.frogsm.instagram_demo.data.token

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AccessTokenData(
    val user_id: String,
    val access_token: String
)