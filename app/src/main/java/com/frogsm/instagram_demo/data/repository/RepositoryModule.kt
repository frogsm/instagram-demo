package com.frogsm.instagram_demo.data.repository

import com.frogsm.instagram_demo.data.token.TokenDataSourceModule
import com.frogsm.instagram_demo.data.token.TokenRepository
import com.frogsm.instagram_demo.data.token.TokenRepositoryImpl
import com.frogsm.instagram_demo.data.user.UserRepository
import com.frogsm.instagram_demo.data.user.UserRepositoryImpl
import dagger.Binds
import dagger.Module

@Module(
    includes = [
        TokenDataSourceModule::class
    ]
)
abstract class RepositoryModule {

    @Binds
    abstract fun bindsTokenRepository(
        repository: TokenRepositoryImpl
    ): TokenRepository

    @Binds
    abstract fun bindsUserRepository(
        repository: UserRepositoryImpl
    ): UserRepository
}