package com.frogsm.instagram_demo.ui.mediacollection

import com.frogsm.instagram_demo.ui.mediacollection.list.MediaCollectionItem

interface MediaCollectionStateBindable {
    var toolbarTitle: String
    var mediaCollectionItems: List<MediaCollectionItem>
}

class MediaCollectionState(
    override var toolbarTitle: String = "",
    override var mediaCollectionItems: List<MediaCollectionItem> = emptyList()
) : MediaCollectionStateBindable {

    fun initialize(userName: String) {
        toolbarTitle = userName
    }

    fun successGetMediaCollection(
        items: List<MediaCollectionItem>
    ) {
        mediaCollectionItems = items
    }

}