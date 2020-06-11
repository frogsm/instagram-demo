package com.frogsm.instagram_demo.ui.mediacollection

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.frogsm.instagram_demo.domain.usecase.media.GetMediaCollection
import com.frogsm.instagram_demo.domain.usecase.media.GetMediaCollectionFromUrl
import com.frogsm.instagram_demo.domain.usecase.user.GetUser
import com.frogsm.instagram_demo.ui.GlobalListener
import com.frogsm.instagram_demo.ui.base.BaseViewModel
import com.frogsm.instagram_demo.ui.mapper.mapToMediaCollectionItems
import com.frogsm.instagram_demo.ui.mapper.mapToUserItem
import com.frogsm.instagram_demo.ui.mediacollection.list.MediaCollectionItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MediaCollectionViewModel @Inject constructor(
    context: Context,
    globalListener: GlobalListener,
    private val getUser: GetUser,
    private val getMediaCollection: GetMediaCollection,
    private val getMediaCollectionFromUrl: GetMediaCollectionFromUrl
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

    override fun onMediaCollectionItemClicked(item: MediaCollectionItem) {
        state.onMediaCollectionItemClicked(item.mediaId)
        liveData.postValue(state)
    }

    override val loadSuccessPageUrlSet: HashSet<String>
        get() = state.loadSuccessPageUrlSet

    override fun onNextPageRequest() {
        viewModelScope.launch {
            launch { getMediaCollectionFromUrl(state.nextPageUrl, state.mediaCollectionItems.size) }
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
        showMediaCollectionProgressBar(true)

        getMediaCollection(Unit)
            .map { it.nextPageUrl to it.mapToMediaCollectionItems(0) }
            .onSuccess { (nextPageUrl, items) ->
                state.successGetMediaCollection(nextPageUrl, items)
                liveData.postValue(state)
            }
            .onFailureAfterHttpExceptionHandle {
                cancel()
                state.failureGetMediaCollection(null)
                liveData.postValue(state)
            }

        showMediaCollectionProgressBar(false)
    }

    private suspend fun getMediaCollectionFromUrl(
        pageUrl: String?,
        lastItemIndex: Int
    ) = withContext(Dispatchers.IO) {

        pageUrl?.let { url ->
            if (loadSuccessPageUrlSet.add(url)) {
                GetMediaCollectionFromUrl.Request(url)
                    .run { getMediaCollectionFromUrl(this) }
                    .map { it.nextPageUrl to it.mapToMediaCollectionItems(lastItemIndex)}
                    .onSuccess { (nextPageUrl, items) ->
                        state.successGetMediaCollection(nextPageUrl, items)
                        liveData.postValue(state)
                    }
                    .onFailureAfterHttpExceptionHandle {
                        cancel()
                        state.failureGetMediaCollection(url)
                        liveData.postValue(state)
                    }
            }
        }
    }

    private fun showMediaCollectionProgressBar(visible: Boolean) {
        state.showMediaCollectionProgressBar(visible)
        liveData.postValue(state)
    }
}