package com.frogsm.instagram_demo.data.di.submodule.repository

import com.frogsm.instagram_demo.data.api.AuthorizationApi
import com.frogsm.instagram_demo.data.authorize.AuthorizeLocalDataSource
import com.frogsm.instagram_demo.data.authorize.AuthorizeRemoteDataSource
import com.frogsm.instagram_demo.data.authorize.AuthorizeRepositoryImpl
import com.frogsm.instagram_demo.data.preferences.Preferences
import com.frogsm.instagram_demo.domain.repository.AuthorizeRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AuthorizeRepositoryModule {

    @Binds
    abstract fun bindsAuthorizeRepository(
        repository: AuthorizeRepositoryImpl
    ): AuthorizeRepository

    companion object {
        @Provides
        @Singleton
        fun providesLocalDataSource(
            preferences: Preferences
        ): AuthorizeLocalDataSource =
            AuthorizeLocalDataSource(preferences)

        @Provides
        fun providesRemoteDataSource(
            authorizationApi: AuthorizationApi
        ): AuthorizeRemoteDataSource =
            AuthorizeRemoteDataSource(
                authorizationApi
            )
    }
}