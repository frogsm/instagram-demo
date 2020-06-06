package com.frogsm.instagram_demo.data.api

import com.frogsm.instagram_demo.data.token.AccessTokenData
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.POST
import retrofit2.http.Query

interface AuthorizationApi {

    /**
     * 실제로 데이터를 가져오지 않고 URL 정보만 받아오기 위해 사용합니다.
     */
    @POST("/oauth/authorize")
    fun getAuthorize(
        @Query("client_id") clientId: String,
        @Query("redirect_uri") redirectUri: String,
        @Query("scope") scope: String,
        @Query("response_type") responseType: String
    ): Call<ResponseBody>

    @POST("/oauth/access_token")
    suspend fun getAccessToken(
        @Query("client_id") clientId: String,
        @Query("client_secret") clientSecretId: String,
        @Query("redirect_uri") redirectUri: String,
        @Query("code") authorizeCode: String,
        @Query("grant_type") grantType: String
    ): AccessTokenData
}