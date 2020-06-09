package com.frogsm.instagram_demo.ui.mediacollection

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.frogsm.instagram_demo.domain.media.GetMediaCollection
import com.frogsm.instagram_demo.ui.GlobalListener
import com.frogsm.instagram_demo.ui.base.BaseViewModel
import com.frogsm.instagram_demo.ui.mapper.mapToMediaCollectionItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MediaCollectionViewModel @Inject constructor(
    globalListener: GlobalListener,
    private val getMediaCollection: GetMediaCollection
) : BaseViewModel(globalListener), MediaCollectionController {

    val liveData = MutableLiveData<MediaCollectionStateBindable>()
    private val state = MediaCollectionState()

    override fun start(userName: String) {
        state.initialize(userName)

        viewModelScope.launch {
            launch { getMediaCollection() }
        }
    }

    private suspend fun getMediaCollection(
    ) = withContext(Dispatchers.IO) {

        getMediaCollection(Unit)
            .map { it.mapToMediaCollectionItem() }
            .onSuccess {
                state.successGetMediaCollection(it)
                liveData.postValue(state)
            }
            .onFailureAfterHttpExceptionHandle {
                cancel()
            }
    }
}