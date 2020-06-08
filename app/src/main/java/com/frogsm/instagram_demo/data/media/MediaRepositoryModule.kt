package com.frogsm.instagram_demo.data.media

import dagger.Binds
import dagger.Module

@Module
abstract class MediaRepositoryModule {

    @Binds
    abstract fun bindsMediaRepository(
        repository: MediaRepositoryImpl
    ): MediaRepository
}