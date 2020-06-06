package com.frogsm.instagram_demo.data.token

import com.frogsm.instagram_demo.domain.entity.AccessToken

interface TokenRepository {
    suspend fun createAccessToken(
        clientId: String,
        clientSecretId: String,
        redirectUri: String,
        authorizeCode: String,
        grantType: String
    )

    suspend fun getAccessToken(): AccessToken?

    suspend fun clearToken()
}