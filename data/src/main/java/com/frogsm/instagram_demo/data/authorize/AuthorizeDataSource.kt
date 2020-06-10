package com.frogsm.instagram_demo.data.authorize

import com.frogsm.instagram_demo.data.authorize.data.AccessTokenData
import com.frogsm.instagram_demo.data.authorize.data.AuthorizeUriData
import com.frogsm.instagram_demo.domain.entity.AccessToken
import javax.inject.Inject

class AuthorizeDataSource @Inject constructor(
    private val localDataSource: AuthorizeLocalDataSource,
    private val remoteDataSource: AuthorizeRemoteDataSource
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