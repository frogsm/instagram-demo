package com.frogsm.instagram_demo.data.user

import com.frogsm.instagram_demo.domain.entity.User
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val userDataSource: UserDataSource
) : UserRepository {

    override suspend fun getUser(): User {
        val data = userDataSource.getUser()

        return User(
            id = data.id,
            name = data.username,
            mediaCount = data.media_count
        )
    }
}