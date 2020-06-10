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
    var mediaCollectionItems: List<MediaCollectionItem>

    // Events
    var showSnackBar: Event<String>?
    var navigateMediaDetail: Event<Pair<String, String>>?
}

class MediaCollectionState(
    private val resources: Resources,
    override var toolbarTitle: String = "",
    override var userItem: UserItem? = null,
    override var progressBarVisible: Boolean = true,
    override var mediaCollectionItems: List<MediaCollectionItem> = emptyList(),
    override var showSnackBar: Event<String>? = null,
    override var navigateMediaDetail: Event<Pair<String, String>>? = null
) : MediaCollectionStateBindable {

    fun initialize(userName: String) {
        toolbarTitle = userName
    }

    fun successGetMediaCollection(
        items: List<MediaCollectionItem>
    ) {
        mediaCollectionItems = items
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

    fun onMediaCollectionItemClicked(mediaId: String) {
        navigateMediaDetail = Event(toolbarTitle to mediaId)
    }

}