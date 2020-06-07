package com.frogsm.instagram_demo.data.token

import com.frogsm.instagram_demo.data.api.AuthorizationApi
import com.frogsm.instagram_demo.data.preferences.Preferences
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class TokenDataSourceModule {

    @Provides
    @Singleton
    fun providesLocalDataSource(
        preferences: Preferences
    ): TokenLocalDataSource = TokenLocalDataSource(preferences)

    @Provides
    fun providesRemoteDataSource(
        authorizationApi: AuthorizationApi
    ): TokenRemoteDataSource = TokenRemoteDataSource(authorizationApi)
}