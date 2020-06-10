package com.frogsm.instagram_demo.data.di.component

import com.frogsm.instagram_demo.data.di.submodule.ApiModule
import com.frogsm.instagram_demo.data.di.submodule.PreferencesModule
import com.frogsm.instagram_demo.data.di.submodule.repository.MediaRepositoryModule
import com.frogsm.instagram_demo.data.di.submodule.repository.TokenRepositoryModule
import com.frogsm.instagram_demo.data.di.submodule.repository.UserRepositoryModule
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