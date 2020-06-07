package com.frogsm.instagram_demo.ui

import androidx.lifecycle.ViewModel
import com.frogsm.instagram_demo.di.custom.ViewModelKey
import com.frogsm.instagram_demo.ui.login.LoginFragment
import com.frogsm.instagram_demo.ui.login.LoginModule
import com.frogsm.instagram_demo.ui.mediacollection.MediaCollectionFragment
import com.frogsm.instagram_demo.ui.mediacollection.MediaCollectionModule
import com.frogsm.instagram_demo.ui.splash.SplashFragment
import com.frogsm.instagram_demo.ui.splash.SplashModule
import com.frogsm.instagram_demo.ui.token.TokenFragment
import com.frogsm.instagram_demo.ui.token.TokenModule
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
abstract class MainActivityModule {

    @Binds
    @IntoMap
    @ViewModelKey(MainActivityViewModel::class)
    abstract fun bindsViewModel(viewModel: MainActivityViewModel): ViewModel

    companion object {
        @Provides
        fun providesGlobalListener(
            mainActivity: MainActivity
        ): GlobalListener = mainActivity.viewModel
    }

    @ContributesAndroidInjector(modules = [SplashModule::class])
    abstract fun bindsSplashFragment(): SplashFragment

    @ContributesAndroidInjector(modules = [LoginModule::class])
    abstract fun bindsLoginFragment(): LoginFragment

    @ContributesAndroidInjector(modules = [TokenModule::class])
    abstract fun bindsTokenFragment(): TokenFragment

    @ContributesAndroidInjector(modules = [MediaCollectionModule::class])
    abstract fun bindsMediaCollectionFragment(): MediaCollectionFragment
}