package com.frogsm.instagram_demo.ui.mediacollection

import com.frogsm.instagram_demo.ui.mediacollection.list.MediaCollectionItem

interface MediaCollectionStateBindable {
    var mediaCollectionItems: List<MediaCollectionItem>
}

class MediaCollectionState(
    override var mediaCollectionItems: List<MediaCollectionItem> = emptyList()
) : MediaCollectionStateBindable {

    fun successGetMediaCollection(
        items: List<MediaCollectionItem>
    ) {
        mediaCollectionItems = items
    }

}