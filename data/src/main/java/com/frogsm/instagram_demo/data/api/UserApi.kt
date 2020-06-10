package com.frogsm.instagram_demo.data.api

import com.frogsm.instagram_demo.data.user.data.UserData
import retrofit2.http.GET
import retrofit2.http.Query

interface UserApi {

    @GET("/me")
    suspend fun getUser(
        @Query("fields") fields: String = "id, username, media_count"
    ): UserData
}