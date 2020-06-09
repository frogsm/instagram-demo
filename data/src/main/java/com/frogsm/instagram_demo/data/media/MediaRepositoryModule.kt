package com.frogsm.instagram_demo.data.media

import com.frogsm.instagram_demo.domain.repository.MediaRepository
import dagger.Binds
import dagger.Module

@Module
abstract class MediaRepositoryModule {

    @Binds
    abstract fun bindsMediaRepository(
        repository: MediaRepositoryImpl
    ): MediaRepository
}