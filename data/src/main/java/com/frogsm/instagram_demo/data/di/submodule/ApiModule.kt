package com.frogsm.instagram_demo.data.di.submodule

import android.content.Context
import com.frogsm.instagram_demo.data.R
import com.frogsm.instagram_demo.data.api.AuthorizationApi
import com.frogsm.instagram_demo.data.api.MediaApi
import com.frogsm.instagram_demo.data.api.UserApi
import com.frogsm.instagram_demo.data.util.MoshiCreator
import com.frogsm.instagram_demo.data.util.ServiceCreator
import com.frogsm.instagram_demo.data.util.TokenInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ApiModule {

    @Provides
    @Singleton
    fun providesAuthorizationApi(
        @ApplicationContext context: Context,
        moshiCreator: MoshiCreator,
        serviceCreator: ServiceCreator
    ): AuthorizationApi = serviceCreator.createService(
        AuthorizationApi::class,
        context.getString(R.string.authorization_host),
        moshiCreator.createMoshi()
    )

    @Provides
    @Singleton
    fun providesUserApi(
        @ApplicationContext context: Context,
        moshiCreator: MoshiCreator,
        serviceCreator: ServiceCreator
    ): UserApi = serviceCreator.createService(
        UserApi::class,
        context.getString(R.string.user_contents_host),
        moshiCreator.createMoshi()
    )

    @Provides
    @Singleton
    fun providesMediaApi(
        @ApplicationContext context: Context,
        moshiCreator: MoshiCreator,
        serviceCreator: ServiceCreator
    ): MediaApi = serviceCreator.createService(
        MediaApi::class,
        context.getString(R.string.user_contents_host),
        moshiCreator.createMoshi()
    )

    @Provides
    @Singleton
    fun providesServiceCreator(
        tokenInterceptor: TokenInterceptor
    ): ServiceCreator = ServiceCreator(tokenInterceptor)
}