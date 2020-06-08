package com.frogsm.instagram_demo.data.user

import com.frogsm.instagram_demo.data.api.UserApi
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
abstract class UserRepositoryModule {

    @Binds
    abstract fun bindsUserRepository(
        repository: UserRepositoryImpl
    ): UserRepository

    companion object {
        @Provides
        @Singleton
        fun providesLocalDataSource(
        ): UserLocalDataSource = UserLocalDataSource()

        @Provides
        fun providesRemoteDataSource(
            userApi: UserApi
        ): UserRemoteDataSource = UserRemoteDataSource(userApi)
    }
}