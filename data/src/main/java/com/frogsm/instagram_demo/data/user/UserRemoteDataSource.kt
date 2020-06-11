package com.frogsm.instagram_demo.data.user

import com.frogsm.instagram_demo.data.api.UserApi
import com.frogsm.instagram_demo.data.user.data.UserData
import javax.inject.Inject

class UserRemoteDataSource @Inject constructor(
    private val userApi: UserApi
) {

    suspend fun getUser(): UserData {
        return userApi.getUser()
    }
}