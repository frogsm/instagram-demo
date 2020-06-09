package com.frogsm.instagram_demo.domain.usecase.token

import com.frogsm.instagram_demo.domain.repository.TokenRepository
import com.frogsm.instagram_demo.domain.usecase.SuspendUseCase
import javax.inject.Inject

class CreateAccessToken @Inject constructor(
    private val tokenRepository: TokenRepository
) : SuspendUseCase<CreateAccessToken.Request, Unit> {

    override suspend fun invoke(param: Request): Result<Unit> = try {
        tokenRepository.createAccessToken(
            clientId = param.clientId,
            clientSecretId = param.clientSecretId,
            redirectUri = param.redirectUri,
            authorizeCode = param.authorizeCode,
            grantType = "authorization_code"
        )

        Result.success(Unit)
    } catch (throwable: Throwable) {
        throwable.printStackTrace()
        Result.failure(throwable)
    }

    class Request(
        val clientId: String,
        val clientSecretId: String,
        val redirectUri: String,
        val authorizeCode: String
    )
}