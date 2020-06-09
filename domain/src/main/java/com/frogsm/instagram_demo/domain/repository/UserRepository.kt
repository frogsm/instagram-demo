package com.frogsm.instagram_demo.domain.repository

import com.frogsm.instagram_demo.domain.entity.User

interface UserRepository {
    suspend fun login(): Boolean

    suspend fun logout()

    suspend fun getUser(): User
}