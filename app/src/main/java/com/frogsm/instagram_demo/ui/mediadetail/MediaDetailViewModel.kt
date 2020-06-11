package com.frogsm.instagram_demo.ui.mediadetail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.frogsm.instagram_demo.domain.usecase.media.GetMediaDetail
import com.frogsm.instagram_demo.ui.GlobalListener
import com.frogsm.instagram_demo.ui.base.BaseViewModel
import com.frogsm.instagram_demo.ui.mapper.mapToMediaDetailItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MediaDetailViewModel @Inject constructor(
    globalListener: GlobalListener,
    private val getMediaDetail: GetMediaDetail
) : BaseViewModel(globalListener), MediaDetailController {

    val liveData = MutableLiveData<MediaDetailStateBindable>()
    private val state = MediaDetailState()

    override fun start(userName: String, mediaId: String) {
        state.initialize(userName)
        liveData.postValue(state)

        viewModelScope.launch {
            launch { getMediaDetail(mediaId) }
        }
    }

    private suspend fun getMediaDetail(
        mediaId: String
    ) = withContext(Dispatchers.IO) {

        GetMediaDetail.Request(mediaId)
            .run { getMediaDetail(this) }
            .map { it.mapToMediaDetailItem() }
            .onSuccess {
                state.successGetMediaDetail(it)
                liveData.postValue(state)
            }
            .onFailureAfterHttpExceptionHandle {
                cancel()
                state.failureGetMediaDetail()
                liveData.postValue(state)
            }
    }
}