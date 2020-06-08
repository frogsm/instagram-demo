package com.frogsm.instagram_demo.data.user

import com.frogsm.instagram_demo.domain.entity.User

interface UserRepository {
    suspend fun login(): User

    suspend fun logout()
}