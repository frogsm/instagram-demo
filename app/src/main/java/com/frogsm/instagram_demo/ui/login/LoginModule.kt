package com.frogsm.instagram_demo.ui.login

import androidx.lifecycle.ViewModel
import com.frogsm.instagram_demo.di.custom.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class LoginModule {
    @Binds
    @IntoMap
    @ViewModelKey(LoginViewModel::class)
    abstract fun bindsViewModel(viewModel: LoginViewModel): ViewModel
}