package com.frogsm.instagram_demo.ui.mediacollection

import android.content.res.Resources
import com.frogsm.instagram_demo.R
import com.frogsm.instagram_demo.ui.mediacollection.list.MediaCollectionItem
import com.frogsm.instagram_demo.ui.mediacollection.user.UserItem
import com.frogsm.instagram_demo.util.Event

interface MediaCollectionStateBindable {
    var toolbarTitle: String
    var userItem: UserItem?
    var progressBarVisible: Boolean
    val mediaCollectionItems: List<MediaCollectionItem>

    // Events
    var showSnackBar: Event<String>?
    var navigateMediaDetail: Event<Pair<String, String>>?
    var commonErrorHandle: Event<Throwable>?
}

class MediaCollectionState(
    private val resources: Resources,
    override var toolbarTitle: String = "",
    override var userItem: UserItem? = null,
    override var progressBarVisible: Boolean = true,
    override var showSnackBar: Event<String>? = null,
    override var navigateMediaDetail: Event<Pair<String, String>>? = null,
    override var commonErrorHandle: Event<Throwable>? = null
) : MediaCollectionStateBindable {

    var nextPageUrl: String? = null
    val loadSuccessPageUrlSet: HashSet<String> = hashSetOf()

    // items
    private val mediaCollectionLock = Any()

    private val innerMediaCollectionItems =
        sortedSetOf<MediaCollectionItem>(compareBy { it.index })

    override val mediaCollectionItems: List<MediaCollectionItem>
        get() = synchronized(mediaCollectionLock) {
            innerMediaCollectionItems.toList()
        }

    private var collectionProgressBarEnabled = true

    fun initialize(userName: String) {
        toolbarTitle = userName
    }

    fun successGetMediaCollection(
        nextPageUrl: String?,
        items: List<MediaCollectionItem>
    ) {
        // 이미 아이템이 있는 경우에는 이 화면으로 돌아오더라도 프로그래스바를 보여주지 않음
        if (innerMediaCollectionItems.isEmpty()) {
            collectionProgressBarEnabled = false
        }

        this.nextPageUrl = nextPageUrl

        synchronized(mediaCollectionLock) {
            innerMediaCollectionItems.addAll(items)
        }
    }

    fun failureGetMediaCollection(url: String?) {
        loadSuccessPageUrlSet.remove(url)
        nextPageUrl = url
    }

    fun successGetUser(item: UserItem) {
        userItem = item
    }

    fun failureGetUser() {
        showSnackBar = Event(resources.getString(R.string.common_error))
    }

    fun showProgressBar(visible: Boolean) {
        progressBarVisible = visible
    }

    fun showMediaCollectionProgressBar(visible: Boolean) {
        progressBarVisible = visible && collectionProgressBarEnabled
    }

    fun onMediaCollectionItemClicked(mediaId: String) {
        navigateMediaDetail = Event(toolbarTitle to mediaId)
    }

    fun commonErrorHandle(throwable: Throwable) {
        commonErrorHandle = Event(throwable)
    }

}