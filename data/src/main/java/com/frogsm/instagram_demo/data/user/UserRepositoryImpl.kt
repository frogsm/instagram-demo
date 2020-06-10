package com.frogsm.instagram_demo.data.user

import com.frogsm.instagram_demo.domain.entity.User
import com.frogsm.instagram_demo.domain.repository.UserRepository
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val userDataSource: UserDataSource
) : UserRepository {

    override suspend fun login(): Boolean {
        userDataSource.getUser()
        return true
    }

    override suspend fun logout() {
        userDataSource.clearUser()
    }

    override suspend fun getUser(): User {
        val data = userDataSource.getUser()

        return User(
            id = data.id,
            name = data.username,
            mediaCount = data.media_count
        )
    }
}