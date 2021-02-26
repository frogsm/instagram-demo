package com.frogsm.instagram_demo.data.di.submodule.repository

import com.frogsm.instagram_demo.data.media.MediaRepositoryImpl
import com.frogsm.instagram_demo.domain.repository.MediaRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

@Module
@InstallIn(ApplicationComponent::class)
abstract class MediaRepositoryModule {

    @Binds
    abstract fun bindsMediaRepository(
        repository: MediaRepositoryImpl
    ): MediaRepository
}