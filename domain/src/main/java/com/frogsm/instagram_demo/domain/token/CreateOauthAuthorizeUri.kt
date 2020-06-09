package com.frogsm.instagram_demo.domain.token

import com.frogsm.instagram_demo.data.api.AuthorizationApi
import com.frogsm.instagram_demo.domain.entity.AuthorizeUri
import com.frogsm.instagram_demo.domain.usecase.SuspendUseCase
import javax.inject.Inject

class CreateOauthAuthorizeUri @Inject constructor(
    private val authorizationApi: AuthorizationApi
) : SuspendUseCase<CreateOauthAuthorizeUri.Request, AuthorizeUri> {

    override suspend fun invoke(param: Request): Result<AuthorizeUri> = try {
        val clientId = param.clientId
        val redirectUri = param.redirectUri
        val scope = "user_profile,user_media"
        val responseType = "code"

        val url = authorizationApi.getAuthorize(clientId, redirectUri, scope, responseType)
            .request()
            .url.toString()

        Result.success(AuthorizeUri(url))
    } catch (throwable: Throwable) {
        throwable.printStackTrace()
        Result.failure(throwable)
    }

    class Request(
        val clientId: String,
        val redirectUri: String
    )
}