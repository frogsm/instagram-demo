package com.frogsm.instagram_demo.data.token

import com.frogsm.instagram_demo.data.token.data.AccessTokenData
import com.frogsm.instagram_demo.data.token.data.AuthorizeUriData
import com.frogsm.instagram_demo.domain.entity.AccessToken
import javax.inject.Inject

class TokenDataSource @Inject constructor(
    private val localDataSource: TokenLocalDataSource,
    private val remoteDataSource: TokenRemoteDataSource
) {

    suspend fun getAuthorizeUri(
        clientId: String,
        redirectUri: String,
        scope: String,
        responseType: String
    ): AuthorizeUriData {
        return remoteDataSource.getAuthorizeUri(
            clientId = clientId,
            redirectUri = redirectUri,
            scope = scope,
            responseType = responseType
        )
    }

    suspend fun createAccessToken(
        clientId: String,
        clientSecretId: String,
        redirectUri: String,
        authorizeCode: String,
        grantType: String
    ): AccessTokenData {
        val tokenData = remoteDataSource.createAccessToken(
            clientId = clientId,
            clientSecretId = clientSecretId,
            redirectUri = redirectUri,
            authorizeCode = authorizeCode,
            grantType = grantType
        )

        localDataSource.updateAccessToken(tokenData)
        return tokenData
    }

    suspend fun getAccessToken(): AccessToken? {
        return localDataSource.getAccessToken()
    }

    suspend fun updateAccessToken(tokenData: AccessTokenData) {
        localDataSource.updateAccessToken(tokenData)
    }

    suspend fun clearAccessToken() {
        localDataSource.clearAccessToken()
    }
}