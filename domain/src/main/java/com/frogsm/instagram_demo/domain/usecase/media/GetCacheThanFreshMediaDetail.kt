package com.frogsm.instagram_demo.domain.usecase.media

import com.frogsm.instagram_demo.domain.entity.MediaDetail
import com.frogsm.instagram_demo.domain.repository.MediaRepository
import com.frogsm.instagram_demo.domain.usecase.FlowUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCacheThanFreshMediaDetail @Inject constructor(
    private val mediaRepository: MediaRepository
): FlowUseCase<GetCacheThanFreshMediaDetail.Request, MediaDetail> {

    override fun invoke(param: Request): Flow<MediaDetail> {
        return mediaRepository.getCacheThenFreshMediaDetail(param.id)
    }

    class Request(
        val id: String
    )
}