package com.frogsm.instagram_demo.data.preferences

import android.content.Context
import android.content.SharedPreferences
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
abstract class PreferencesModule {

    companion object {
        @Provides
        @Singleton
        fun providesSharedPreferences(
            context: Context
        ): SharedPreferences = context.getSharedPreferences(
            "DataPreferences",
            Context.MODE_PRIVATE
        )
    }

    @Binds
    abstract fun bindsPreferences(preferencesImpl: PreferencesImpl): Preferences
}