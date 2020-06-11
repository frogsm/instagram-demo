package com.frogsm.instagram_demo.domain.usecase.login

import com.frogsm.instagram_demo.domain.repository.AuthorizeRepository
import com.frogsm.instagram_demo.domain.repository.UserRepository
import com.frogsm.instagram_demo.domain.usecase.SuspendUseCase
import javax.inject.Inject

class ExpireLogin @Inject constructor(
    private val userRepository: UserRepository,
    private val authorizeRepository: AuthorizeRepository
) : SuspendUseCase<Unit, Unit> {

    override suspend fun invoke(param: Unit): Result<Unit> = try {
        userRepository.logout()
        authorizeRepository.clearToken()

        Result.success(Unit)
    } catch (throwable: Throwable) {
        throwable.printStackTrace()
        Result.failure(throwable)
    }
}