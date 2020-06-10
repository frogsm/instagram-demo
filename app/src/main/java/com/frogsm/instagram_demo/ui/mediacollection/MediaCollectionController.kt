package com.frogsm.instagram_demo.ui.mediacollection

import com.frogsm.instagram_demo.ui.mediacollection.list.MediaCollectionItem

interface MediaCollectionController {
    fun start(userName: String)

    fun onMediaCollectionItemClicked(item: MediaCollectionItem)
}