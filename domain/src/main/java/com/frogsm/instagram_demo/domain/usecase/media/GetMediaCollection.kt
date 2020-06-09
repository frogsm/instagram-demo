package com.frogsm.instagram_demo.domain.usecase.media

import com.frogsm.instagram_demo.domain.entity.MediaCollection
import com.frogsm.instagram_demo.domain.repository.MediaRepository
import com.frogsm.instagram_demo.domain.usecase.SuspendUseCase
import javax.inject.Inject

class GetMediaCollection @Inject constructor(
    private val mediaRepository: MediaRepository
) : SuspendUseCase<Unit, MediaCollection> {

    override suspend fun invoke(param: Unit): Result<MediaCollection> = try {
        val mediaCollection = mediaRepository.getMediaCollection()

        Result.success(mediaCollection)
    } catch (throwable: Throwable) {
        throwable.printStackTrace()
        Result.failure(throwable)
    }
}