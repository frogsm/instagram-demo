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

    /**
     * TODO: 2020/09/22 추후 제거하는게 좋을 듯.. 힐트에서 지원하지 않음 임시방편으로 처리
     * cc. https://stackoverflow.com/questions/62691794/how-to-pass-arguments-to-hilt-module
     */
    companion object {
        @Provides
        fun providesController(
            fragment: Fragment
        ): MediaCollectionController = (fragment as MediaCollectionFragment).viewModel
    }
}