package com.frogsm.instagram_demo.data

import com.frogsm.instagram_demo.data.api.ApiModule
import com.frogsm.instagram_demo.data.media.MediaRepositoryModule
import com.frogsm.instagram_demo.data.preferences.PreferencesModule
import com.frogsm.instagram_demo.data.token.TokenRepositoryModule
import com.frogsm.instagram_demo.data.user.UserRepositoryModule
import dagger.Module

@Module(
    includes = [
        ApiModule::class,
        MediaRepositoryModule::class,
        PreferencesModule::class,
        TokenRepositoryModule::class,
        UserRepositoryModule::class
    ]
)
abstract class DataModule