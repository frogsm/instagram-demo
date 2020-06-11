package com.frogsm.instagram_demo.domain.usecase.media

import com.frogsm.instagram_demo.domain.entity.MediaCollection
import com.frogsm.instagram_demo.domain.repository.MediaRepository
import com.frogsm.instagram_demo.domain.usecase.SuspendUseCase
import javax.inject.Inject

class GetMediaCollectionFromUrl @Inject constructor(
    private val mediaRepository: MediaRepository
) : SuspendUseCase<GetMediaCollectionFromUrl.Request, MediaCollection> {

    override suspend fun invoke(param: Request): Result<MediaCollection> = try {
        val mediaCollection = mediaRepository.getMediaCollection(param.url)

        Result.success(mediaCollection)
    } catch (throwable: Throwable) {
        throwable.printStackTrace()
        Result.failure(throwable)
    }

    class Request(
        val url: String
    )
}