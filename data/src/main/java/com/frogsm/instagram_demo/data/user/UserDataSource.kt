package com.frogsm.instagram_demo.data.user

import com.frogsm.instagram_demo.data.user.data.UserData
import javax.inject.Inject

class UserDataSource @Inject constructor(
    private val localDataSource: UserLocalDataSource,
    private val remoteDataSource: UserRemoteDataSource
) {

    suspend fun getUser(): UserData {
        return localDataSource.user
            ?: remoteDataSource.getUser()
                .also { localDataSource.user = it }
    }

    suspend fun clearUser() {
        localDataSource.user = null
    }
}