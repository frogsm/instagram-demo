package com.frogsm.instagram_demo.di.component

import com.frogsm.instagram_demo.ui.MainActivity
import com.frogsm.instagram_demo.ui.MainActivityModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModule {

    @ContributesAndroidInjector(modules = [MainActivityModule::class])
    abstract fun bindsMainActivity(): MainActivity
}