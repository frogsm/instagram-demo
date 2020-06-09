package com.frogsm.instagram_demo.data.token

import com.frogsm.instagram_demo.domain.entity.AccessToken
import com.frogsm.instagram_demo.domain.entity.AuthorizeUri
import com.frogsm.instagram_demo.domain.repository.TokenRepository
import javax.inject.Inject

class TokenRepositoryImpl @Inject constructor(
    private val tokenDataSource: TokenDataSource
) : TokenRepository {

    override suspend fun getAuthorizeUri(
        clientId: String,
        redirectUri: String,
        scope: String,
        responseType: String
    ): AuthorizeUri {
        val data = tokenDataSource.getAuthorizeUri(
            clientId = clientId,
            redirectUri = redirectUri,
            scope = scope,
            responseType = responseType
        )
        return AuthorizeUri(data.uri)
    }

    override suspend fun createAccessToken(
        clientId: String,
        clientSecretId: String,
        redirectUri: String,
        authorizeCode: String,
        grantType: String
    ) {
        tokenDataSource.createAccessToken(
            clientId = clientId,
            clientSecretId = clientSecretId,
            redirectUri = redirectUri,
            authorizeCode = authorizeCode,
            grantType = grantType
        )
    }

    override suspend fun getAccessToken(): AccessToken? {
        return tokenDataSource.getAccessToken()
    }

    override suspend fun clearToken() {
        tokenDataSource.clearAccessToken()
    }
}