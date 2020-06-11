package com.frogsm.instagram_demo.data.authorize

import com.frogsm.instagram_demo.domain.entity.AccessToken
import com.frogsm.instagram_demo.domain.entity.AuthorizeUri
import com.frogsm.instagram_demo.domain.repository.AuthorizeRepository
import javax.inject.Inject

class AuthorizeRepositoryImpl @Inject constructor(
    private val authorizeDataSource: AuthorizeDataSource
) : AuthorizeRepository {

    override suspend fun getAuthorizeUri(
        clientId: String,
        redirectUri: String,
        scope: String,
        responseType: String
    ): AuthorizeUri {
        val data = authorizeDataSource.getAuthorizeUri(
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
        authorizeDataSource.createAccessToken(
            clientId = clientId,
            clientSecretId = clientSecretId,
            redirectUri = redirectUri,
            authorizeCode = authorizeCode,
            grantType = grantType
        )
    }

    override suspend fun getAccessToken(): AccessToken? {
        return authorizeDataSource.getAccessToken()
    }

    override suspend fun clearToken() {
        authorizeDataSource.clearAccessToken()
    }
}