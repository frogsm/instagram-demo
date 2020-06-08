package com.frogsm.instagram_demo.domain.token

import com.frogsm.instagram_demo.data.user.UserRepository
import com.frogsm.instagram_demo.domain.usecase.SuspendUseCase
import javax.inject.Inject

class ValidateAccessToken @Inject constructor(
    private val userRepository: UserRepository
) : SuspendUseCase<Unit, Unit> {

    override suspend fun invoke(param: Unit): Result<Unit> = try {
        /**
         * 토큰이 만료되지 않았다면, 유스케이스 통과
         * 토큰이 만료되었다면, 글로벌 핸들러로 처리하기 때문에 별도로 처리하지 않음
         */
        userRepository.login()

        Result.success(Unit)
    } catch (throwable: Throwable) {
        throwable.printStackTrace()
        Result.failure(throwable)
    }
}