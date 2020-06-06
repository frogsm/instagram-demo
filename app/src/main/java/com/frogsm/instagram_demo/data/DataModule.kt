package com.frogsm.instagram_demo.data

import com.frogsm.instagram_demo.data.api.ApiModule
import com.frogsm.instagram_demo.data.preferences.PreferencesModule
import com.frogsm.instagram_demo.data.repository.RepositoryModule
import dagger.Module

@Module(
    includes = [
        ApiModule::class,
        RepositoryModule::class,
        PreferencesModule::class
    ]
)
abstract class DataModule