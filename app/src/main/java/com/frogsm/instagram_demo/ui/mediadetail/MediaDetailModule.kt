package com.frogsm.instagram_demo.ui.mediadetail

import androidx.lifecycle.ViewModel
import com.frogsm.instagram_demo.di.custom.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class MediaDetailModule {

    @Binds
    @IntoMap
    @ViewModelKey(MediaDetailViewModel::class)
    abstract fun bindsViewModel(viewModel: MediaDetailViewModel): ViewModel
}