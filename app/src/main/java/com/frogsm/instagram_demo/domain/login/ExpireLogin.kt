package com.frogsm.instagram_demo.domain.login

import com.frogsm.instagram_demo.data.token.TokenRepository
import com.frogsm.instagram_demo.domain.usecase.SuspendUseCase
import javax.inject.Inject

class ExpireLogin @Inject constructor(
    private val tokenRepository: TokenRepository
) : SuspendUseCase<Unit, Unit> {

    override suspend fun invoke(param: Unit): Result<Unit> = try {
        tokenRepository.clearToken()

        Result.success(Unit)
    } catch (throwable: Throwable) {
        throwable.printStackTrace()
        Result.failure(throwable)
    }
}