package com.frogsm.instagram_demo.data.user

import com.frogsm.instagram_demo.data.api.UserApi
import javax.inject.Inject

class UserDataSource @Inject constructor(
    private val userApi: UserApi
) {

    suspend fun getUser(): UserData {
        return userApi.getUser()
    }
}