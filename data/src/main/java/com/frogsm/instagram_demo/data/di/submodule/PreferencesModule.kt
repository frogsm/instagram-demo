package com.frogsm.instagram_demo.data.di.submodule

import android.content.Context
import android.content.SharedPreferences
import com.frogsm.instagram_demo.data.preferences.Preferences
import com.frogsm.instagram_demo.data.preferences.PreferencesImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
abstract class PreferencesModule {

    companion object {
        @Provides
        @Singleton
        fun providesSharedPreferences(
            @ApplicationContext context: Context
        ): SharedPreferences = context.getSharedPreferences(
            "DataPreferences",
            Context.MODE_PRIVATE
        )
    }

    @Binds
    abstract fun bindsPreferences(preferencesImpl: PreferencesImpl): Preferences
}