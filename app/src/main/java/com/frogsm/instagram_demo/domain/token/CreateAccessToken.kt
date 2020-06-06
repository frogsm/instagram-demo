package com.frogsm.instagram_demo.domain.token

import com.frogsm.instagram_demo.domain.usecase.SuspendUseCase
import javax.inject.Inject

class CreateAccessToken @Inject constructor(

) : SuspendUseCase<CreateAccessToken.Request, Unit> {

    override suspend fun invoke(param: Request): Result<Unit> {
        TODO("Not yet implemented")
    }

    class Request(
        val clientId: String,
        val clientSecretId: String,
        val redirectUri: String,
        val authorizeCode: String
    )
}