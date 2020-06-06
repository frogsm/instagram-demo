package com.frogsm.instagram_demo.data.api

import android.content.Context
import com.frogsm.instagram_demo.R
import com.frogsm.instagram_demo.data.util.MoshiCreator
import com.frogsm.instagram_demo.data.util.ServiceCreator
import dagger.Module
import dagger.Provides

@Module
class ApiModule {

    @Provides
    fun providesAuthorizationApi(
        context: Context,
        moshiCreator: MoshiCreator,
        serviceCreator: ServiceCreator
    ): AuthorizationApi = serviceCreator.createService(
        AuthorizationApi::class,
        context.getString(R.string.authorization_host),
        moshiCreator.createMoshi()
    )

    @Provides
    fun providesUserContentsApi(
        context: Context,
        moshiCreator: MoshiCreator,
        serviceCreator: ServiceCreator
    ): UserContentsApi = serviceCreator.createService(
        UserContentsApi::class,
        context.getString(R.string.user_contents_host),
        moshiCreator.createMoshi()
    )
}