package com.frogsm.instagram_demo.ui.mediacollection

import androidx.lifecycle.ViewModel
import com.frogsm.instagram_demo.di.custom.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class MediaCollectionModule {
    @Binds
    @IntoMap
    @ViewModelKey(MediaCollectionViewModel::class)
    abstract fun bindsViewModel(viewModel: MediaCollectionViewModel): ViewModel
}