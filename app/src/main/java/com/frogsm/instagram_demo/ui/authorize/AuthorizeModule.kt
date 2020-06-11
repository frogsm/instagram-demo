package com.frogsm.instagram_demo.ui.authorize

import androidx.lifecycle.ViewModel
import com.frogsm.instagram_demo.di.custom.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class AuthorizeModule {
    @Binds
    @IntoMap
    @ViewModelKey(AuthorizeViewModel::class)
    abstract fun bindsViewModel(viewModel: AuthorizeViewModel): ViewModel
}