package com.frogsm.instagram_demo.ui.mediacollection

import androidx.fragment.app.Fragment
import com.frogsm.instagram_demo.ui.mediacollection.list.MediaCollectionDelegate
import com.frogsm.instagram_demo.ui.mediacollection.list.MediaCollectionDelegateImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent

@Module
@InstallIn(FragmentComponent::class)
abstract class MediaCollectionModule {

    @Binds
    abstract fun bindsViewHolderDelegate(
        mediaCollectionDelegateImpl: MediaCollectionDelegateImpl
    ): MediaCollectionDelegate

    companion object {
        @Provides
        fun providesController(
            fragment: Fragment
        ): MediaCollectionController = (fragment as MediaCollectionFragment).viewModel
    }
}