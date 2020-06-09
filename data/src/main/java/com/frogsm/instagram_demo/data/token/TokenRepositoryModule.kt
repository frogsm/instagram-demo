package com.frogsm.instagram_demo.data.token

import com.frogsm.instagram_demo.data.api.AuthorizationApi
import com.frogsm.instagram_demo.data.preferences.Preferences
import com.frogsm.instagram_demo.domain.repository.TokenRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
abstract class TokenRepositoryModule {

    @Binds
    abstract fun bindsTokenRepository(
        repository: TokenRepositoryImpl
    ): TokenRepository

    companion object {
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
}