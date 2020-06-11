package com.frogsm.instagram_demo.ui.mediadetail

import com.frogsm.instagram_demo.ui.mediadetail.detail.MediaDetailItem

interface MediaDetailStateBindable {
    var toolbarTitle: String
    val indicatorVisible: Boolean
    var mediaDetailItem: MediaDetailItem
}

class MediaDetailState(
    override var toolbarTitle: String = "",
    override var mediaDetailItem: MediaDetailItem = MediaDetailItem.EMPTY
) : MediaDetailStateBindable {

    override val indicatorVisible: Boolean
        get() = mediaDetailItem.children.size > 1

    fun initialize(userName: String) {
        toolbarTitle = userName
    }

    fun successGetMediaDetail(item: MediaDetailItem) {
        mediaDetailItem = item
    }

    fun failureGetMediaDetail() {

    }
}