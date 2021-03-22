package com.frogsm.instagram_demo.data.di.submodule.repository

import com.frogsm.instagram_demo.data.api.UserApi
import com.frogsm.instagram_demo.data.user.UserLocalDataSource
import com.frogsm.instagram_demo.data.user.UserRemoteDataSource
import com.frogsm.instagram_demo.data.user.UserRepositoryImpl
import com.frogsm.instagram_demo.domain.repository.UserRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class UserRepositoryModule {

    @Binds
    abstract fun bindsUserRepository(
        repository: UserRepositoryImpl
    ): UserRepository

    companion object {
        @Provides
        @Singleton
        fun providesLocalDataSource(
        ): UserLocalDataSource =
            UserLocalDataSource()

        @Provides
        fun providesRemoteDataSource(
            userApi: UserApi
        ): UserRemoteDataSource =
            UserRemoteDataSource(userApi)
    }
}