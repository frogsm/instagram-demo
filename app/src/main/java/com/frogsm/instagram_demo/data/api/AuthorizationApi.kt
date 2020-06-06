package com.frogsm.instagram_demo.data.api

import com.frogsm.instagram_demo.data.token.AccessTokenData
import retrofit2.http.POST
import retrofit2.http.Query

interface AuthorizationApi {

    @POST("/oauth/access_token")
    suspend fun getAccessToken(
        @Query("client_id") clientId: String,
        @Query("client_secret") clientSecret: String,
        @Query("code") code: String,
        @Query("grant_type") grantType: String,
        @Query("redirect_uri") redirectUri: String
    ): AccessTokenData
}