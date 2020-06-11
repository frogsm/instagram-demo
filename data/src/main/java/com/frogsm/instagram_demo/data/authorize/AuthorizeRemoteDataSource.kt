package com.frogsm.instagram_demo.data.authorize

import com.frogsm.instagram_demo.data.api.AuthorizationApi
import com.frogsm.instagram_demo.data.authorize.data.AccessTokenData
import com.frogsm.instagram_demo.data.authorize.data.AuthorizeUriData
import javax.inject.Inject

class AuthorizeRemoteDataSource @Inject constructor(
    private val authorizationApi: AuthorizationApi
) {

    suspend fun getAuthorizeUri(
        clientId: String,
        redirectUri: String,
        scope: String,
        responseType: String
    ): AuthorizeUriData {
        val uri = authorizationApi.getAuthorize(clientId, redirectUri, scope, responseType)
            .request()
            .url.toString()

        return AuthorizeUriData(uri)
    }

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