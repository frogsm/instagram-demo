package com.frogsm.instagram_demo.domain.repository

import com.frogsm.instagram_demo.domain.entity.AccessToken
import com.frogsm.instagram_demo.domain.entity.AuthorizeUri

interface AuthorizeRepository {
    suspend fun getAuthorizeUri(
        clientId: String,
        redirectUri: String,
        scope: String,
        responseType: String
    ): AuthorizeUri

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