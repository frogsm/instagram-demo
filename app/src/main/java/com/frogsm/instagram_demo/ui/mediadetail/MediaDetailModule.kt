package com.frogsm.instagram_demo.ui.mediadetail

import com.frogsm.instagram_demo.ui.mediadetail.list.MediaDetailDelegate
import com.frogsm.instagram_demo.ui.mediadetail.list.MediaDetailDelegateImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent

@Module
@InstallIn(FragmentComponent::class)
abstract class MediaDetailModule {

    @Binds
    abstract fun bindsViewHolderDelegate(
        mediaDetailDelegateImpl: MediaDetailDelegateImpl
    ): MediaDetailDelegate
}