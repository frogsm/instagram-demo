package com.frogsm.instagram_demo.ui.mediadetail

import com.frogsm.instagram_demo.ui.mediadetail.detail.MediaDetailItem

interface MediaDetailStateBindable {
    var toolbarTitle: String
    val indicatorVisible: Boolean
    val authorText: String
    val summaryContentText: String
    val extraContentText: String
    var mediaDetailItem: MediaDetailItem
}

class MediaDetailState(
    override var toolbarTitle: String = "",
    override var mediaDetailItem: MediaDetailItem = MediaDetailItem.EMPTY
) : MediaDetailStateBindable {

    override val indicatorVisible: Boolean
        get() = mediaDetailItem.children.size > 1

    override val authorText: String
        get() = mediaDetailItem.userName

    override val summaryContentText: String
        get() = mediaDetailItem.summaryCaption

    override val extraContentText: String
        get() = mediaDetailItem.extraCaption

    fun initialize(userName: String) {
        toolbarTitle = userName
    }

    fun successGetMediaDetail(item: MediaDetailItem) {
        mediaDetailItem = item
    }

    fun failureGetMediaDetail() {

    }
}