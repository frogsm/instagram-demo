package com.frogsm.instagram_demo.data.user

import com.frogsm.instagram_demo.domain.entity.User
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val userDataSource: UserDataSource
) : UserRepository {

    override suspend fun login(): Boolean {
        val user = userDataSource.getUser()
        return true
    }

    override suspend fun logout() {
        userDataSource.clearUser()
    }

    override suspend fun getUser(): User {
        val user = userDataSource.getUser()

        return User(
            id = user.id,
            name = user.username,
            mediaCount = user.media_count
        )
    }
}