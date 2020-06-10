package com.frogsm.instagram_demo.domain.usecase.authorize

import com.frogsm.instagram_demo.domain.repository.AuthorizeRepository
import com.frogsm.instagram_demo.domain.usecase.SuspendUseCase
import javax.inject.Inject

class ClearAccessToken @Inject constructor(
    private val authorizeRepository: AuthorizeRepository
) : SuspendUseCase<Unit, Unit> {

    override suspend fun invoke(param: Unit): Result<Unit> = try {
        authorizeRepository.clearToken()

        Result.success(Unit)
    } catch (throwable: Throwable) {
        throwable.printStackTrace()
        Result.failure(throwable)
    }
}