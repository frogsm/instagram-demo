package com.frogsm.instagram_demo.ui.mediadetail

import com.frogsm.instagram_demo.ui.mediadetail.detail.MediaDetailItem

interface MediaDetailStateBindable {
    var toolbarTitle: String
    var mediaDetailItem: MediaDetailItem
}

class MediaDetailState(
    override var toolbarTitle: String = "",
    override var mediaDetailItem: MediaDetailItem = MediaDetailItem.EMPTY
) : MediaDetailStateBindable {

    fun initialize(userName: String) {
        toolbarTitle = userName
    }

    fun successGetMediaDetail(item: MediaDetailItem) {
        mediaDetailItem = item
    }

    fun failureGetMediaDetail() {

    }
}