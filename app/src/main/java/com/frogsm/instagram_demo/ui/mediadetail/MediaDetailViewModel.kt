package com.frogsm.instagram_demo.ui.mediadetail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.frogsm.instagram_demo.domain.usecase.media.GetCacheThanFreshMediaDetail
import com.frogsm.instagram_demo.ui.GlobalListener
import com.frogsm.instagram_demo.ui.base.BaseViewModel
import com.frogsm.instagram_demo.ui.mapper.mapToMediaDetailItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MediaDetailViewModel @Inject constructor(
    globalListener: GlobalListener,
    private val getCacheThanFreshMediaDetail: GetCacheThanFreshMediaDetail
) : BaseViewModel(globalListener), MediaDetailController {

    val liveData = MutableLiveData<MediaDetailStateBindable>()
    private val state = MediaDetailState()

    override fun start(userName: String, mediaId: String) {
        state.initialize(userName)
        liveData.postValue(state)

        viewModelScope.launch {
            launch { getCacheThanFreshMediaDetail(mediaId) }
        }
    }

    private suspend fun getCacheThanFreshMediaDetail(
        mediaId: String
    ) = withContext(Dispatchers.IO) {

        GetCacheThanFreshMediaDetail.Request(mediaId)
            .run { getCacheThanFreshMediaDetail(this) }
            .onStart { showProgressBar(true) }
            .onCompletion { showProgressBar(false) }
            .map { it.mapToMediaDetailItem() }
            .catch {
                cancel()
                state.failureGetMediaDetail()
                liveData.postValue(state)
            }
            .collect {
                state.successGetMediaDetail(it)
                liveData.postValue(state)
            }
    }

    private fun showProgressBar(visible: Boolean) {
        state.showProgressBar(visible)
        liveData.postValue(state)
    }
}