package com.frogsm.instagram_demo.domain.login

import com.frogsm.instagram_demo.domain.usecase.SuspendUseCase
import com.frogsm.instagram_demo.domain.util.URLValidator
import javax.inject.Inject

class ValidateLogin @Inject constructor(
) : SuspendUseCase<ValidateLogin.Request, Unit> {

    override suspend fun invoke(param: Request): Result<Unit> = try {
        if (URLValidator.isValid(param.redirectUri).not()) {
            throw Exception.InvalidateRedirectUri
        }

        if (param.redirectUri.endsWith('/').not()) {
            throw Exception.NotFoundedRedirectUriEndSlash
        }

        Result.success(Unit)
    } catch (throwable: Throwable) {
        throwable.printStackTrace()
        Result.failure(throwable)
    }

    class Request(
        val clientId: String,
        val redirectUri: String
    )

    sealed class Exception : RuntimeException() {
        object InvalidateRedirectUri : Exception()
        object NotFoundedRedirectUriEndSlash : Exception()
    }
}