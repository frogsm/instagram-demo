package com.frogsm.instagram_demo.data.repository

import com.frogsm.instagram_demo.data.token.TokenRepository
import com.frogsm.instagram_demo.data.token.TokenRepositoryImpl
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindsTokenRepository(
        repository: TokenRepositoryImpl
    ): TokenRepository
}