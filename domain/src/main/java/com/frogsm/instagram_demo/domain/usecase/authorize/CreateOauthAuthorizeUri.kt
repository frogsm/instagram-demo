package com.frogsm.instagram_demo.domain.usecase.authorize

import com.frogsm.instagram_demo.domain.entity.AuthorizeUri
import com.frogsm.instagram_demo.domain.repository.AuthorizeRepository
import com.frogsm.instagram_demo.domain.usecase.SuspendUseCase
import javax.inject.Inject

class CreateOauthAuthorizeUri @Inject constructor(
    private val authorizeRepository: AuthorizeRepository
) : SuspendUseCase<CreateOauthAuthorizeUri.Request, AuthorizeUri> {

    override suspend fun invoke(param: Request): Result<AuthorizeUri> = try {
        val authorizeUri = authorizeRepository.getAuthorizeUri(
            clientId = param.clientId,
            redirectUri = param.redirectUri,
            scope = "user_profile,user_media",
            responseType = "code"
        )

        Result.success(authorizeUri)
    } catch (throwable: Throwable) {
        throwable.printStackTrace()
        Result.failure(throwable)
    }

    class Request(
        val clientId: String,
        val redirectUri: String
    )
}