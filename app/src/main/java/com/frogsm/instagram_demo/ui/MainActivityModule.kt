package com.frogsm.instagram_demo.ui

import com.frogsm.instagram_demo.ui.login.LoginFragment
import com.frogsm.instagram_demo.ui.login.LoginModule
import com.frogsm.instagram_demo.ui.splash.SplashFragment
import com.frogsm.instagram_demo.ui.splash.SplashModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MainActivityModule {

    @ContributesAndroidInjector(modules = [SplashModule::class])
    abstract fun bindsSplashFragment(): SplashFragment

    @ContributesAndroidInjector(modules = [LoginModule::class])
    abstract fun bindsLoginFragment(): LoginFragment
}