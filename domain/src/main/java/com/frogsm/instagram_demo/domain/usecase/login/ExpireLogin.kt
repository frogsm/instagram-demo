package com.frogsm.instagram_demo.domain.usecase.login

import com.frogsm.instagram_demo.domain.repository.TokenRepository
import com.frogsm.instagram_demo.domain.repository.UserRepository
import com.frogsm.instagram_demo.domain.usecase.SuspendUseCase
import javax.inject.Inject

class ExpireLogin @Inject constructor(
    private val userRepository: UserRepository,
    private val tokenRepository: TokenRepository
) : SuspendUseCase<Unit, Unit> {

    override suspend fun invoke(param: Unit): Result<Unit> = try {
        userRepository.logout()
        tokenRepository.clearToken()

        Result.success(Unit)
    } catch (throwable: Throwable) {
        throwable.printStackTrace()
        Result.failure(throwable)
    }
}