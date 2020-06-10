package com.frogsm.instagram_demo.ui.mediacollection

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.frogsm.instagram_demo.domain.usecase.media.GetMediaCollection
import com.frogsm.instagram_demo.domain.usecase.user.GetUser
import com.frogsm.instagram_demo.ui.GlobalListener
import com.frogsm.instagram_demo.ui.base.BaseViewModel
import com.frogsm.instagram_demo.ui.mapper.mapToMediaCollectionItem
import com.frogsm.instagram_demo.ui.mapper.mapToUserItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MediaCollectionViewModel @Inject constructor(
    context: Context,
    globalListener: GlobalListener,
    private val getUser: GetUser,
    private val getMediaCollection: GetMediaCollection
) : BaseViewModel(globalListener), MediaCollectionController {

    val liveData = MutableLiveData<MediaCollectionStateBindable>()
    private val state = MediaCollectionState(context.resources)

    override fun start(userName: String) {
        state.initialize(userName)

        viewModelScope.launch {
            launch { getUser() }
            launch { getMediaCollection() }
        }
    }

    private suspend fun getUser(
    ) = withContext(Dispatchers.IO) {

        getUser(Unit)
            .map { it.mapToUserItem() }
            .onSuccess {
                state.successGetUser(it)
                liveData.postValue(state)
            }
            .onFailure {
                state.failureGetUser()
                liveData.postValue(state)
            }
    }

    private suspend fun getMediaCollection(
    ) = withContext(Dispatchers.IO) {
        showProgressBar(true)

        getMediaCollection(Unit)
            .map { it.mapToMediaCollectionItem() }
            .onSuccess {
                state.successGetMediaCollection(it)
                liveData.postValue(state)
            }
            .onFailureAfterHttpExceptionHandle {
                cancel()
            }

        showProgressBar(false)
    }

    private suspend fun showProgressBar(visible: Boolean) {
        state.showProgressBar(visible)
        liveData.postValue(state)
    }
}