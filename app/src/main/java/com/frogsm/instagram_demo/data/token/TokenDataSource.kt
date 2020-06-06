package com.frogsm.instagram_demo.data.token

import com.frogsm.instagram_demo.domain.entity.AccessToken
import javax.inject.Inject

class TokenDataSource @Inject constructor(
    private val localDataSource: TokenLocalDataSource,
    private val remoteDataSource: TokenRemoteDataSource
) {
    suspend fun createAccessToken(
        clientId: String,
        clientSecretId: String,
        redirectUri: String,
        authorizeCode: String,
        grantType: String
    ): AccessTokenData {
        return remoteDataSource.createAccessToken(
            clientId = clientId,
            clientSecretId = clientSecretId,
            redirectUri = redirectUri,
            authorizeCode = authorizeCode,
            grantType = grantType
        )
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