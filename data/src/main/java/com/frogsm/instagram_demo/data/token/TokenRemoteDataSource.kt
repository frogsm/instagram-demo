package com.frogsm.instagram_demo.data.token

import com.frogsm.instagram_demo.data.api.AuthorizationApi
import javax.inject.Inject

class TokenRemoteDataSource @Inject constructor(
    private val authorizationApi: AuthorizationApi
) {

    suspend fun createAccessToken(
        clientId: String,
        clientSecretId: String,
        redirectUri: String,
        authorizeCode: String,
        grantType: String
    ): AccessTokenData {
        return authorizationApi.getAccessToken(
            clientId, clientSecretId, redirectUri, authorizeCode, grantType
        )
    }
}