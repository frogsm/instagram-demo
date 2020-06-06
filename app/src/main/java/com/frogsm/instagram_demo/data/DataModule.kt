package com.frogsm.instagram_demo.data

import com.frogsm.instagram_demo.data.preferences.PreferencesModule
import dagger.Module

@Module(
    includes = [
        PreferencesModule::class
    ]
)
abstract class DataModule