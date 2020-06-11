package com.frogsm.instagram_demo.domain.usecase.user

import com.frogsm.instagram_demo.domain.entity.User
import com.frogsm.instagram_demo.domain.repository.UserRepository
import com.frogsm.instagram_demo.domain.usecase.SuspendUseCase
import javax.inject.Inject

class GetUser @Inject constructor(
    private val userRepository: UserRepository
) : SuspendUseCase<Unit, User> {

    override suspend fun invoke(param: Unit): Result<User> = try {
        val user = userRepository.getUser()

        Result.success(user)
    } catch (throwable: Throwable) {
        throwable.printStackTrace()
        Result.failure(throwable)
    }
}