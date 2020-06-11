package com.frogsm.instagram_demo.domain.usecase.media

import com.frogsm.instagram_demo.domain.entity.MediaDetail
import com.frogsm.instagram_demo.domain.repository.MediaRepository
import com.frogsm.instagram_demo.domain.usecase.SuspendUseCase
import javax.inject.Inject

class GetMediaDetail @Inject constructor(
    private val mediaRepository: MediaRepository
) : SuspendUseCase<GetMediaDetail.Request, MediaDetail> {

    override suspend fun invoke(param: Request): Result<MediaDetail> = try {
        val mediaDetail = mediaRepository.getMediaDetail(param.id)

        Result.success(mediaDetail)
    } catch (throwable: Throwable) {
        throwable.printStackTrace()
        Result.failure(throwable)
    }

    class Request(
        val id: String
    )
}