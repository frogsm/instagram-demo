package com.frogsm.instagram_demo.ui.token

import androidx.lifecycle.ViewModel
import com.frogsm.instagram_demo.di.custom.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class TokenModule {
    @Binds
    @IntoMap
    @ViewModelKey(TokenViewModel::class)
    abstract fun bindsViewModel(viewModel: TokenViewModel): ViewModel
}