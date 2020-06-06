package com.frogsm.instagram_demo.data.token

import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class TokenDataSourceModule {

    @Provides
    fun providesLocalDataSource(
        dataSource: TokenLocalDataSource
    ): TokenLocalDataSource = dataSource

    @Provides
    fun providesRemoteDataSource(
        dataSource: TokenRemoteDataSource
    ): TokenRemoteDataSource = dataSource

    @Provides
    @Singleton
    fun providesTokenDataSource(
        local: TokenLocalDataSource,
        remote: TokenRemoteDataSource
    ): TokenDataSource = TokenDataSource(local, remote)
}