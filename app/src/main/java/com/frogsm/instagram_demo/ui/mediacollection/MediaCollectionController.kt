package com.frogsm.instagram_demo.ui.mediacollection

import com.frogsm.instagram_demo.ui.mediacollection.list.MediaCollectionItem
import com.frogsm.instagram_demo.util.Pageable

interface MediaCollectionController : Pageable {
    fun start(userName: String)

    fun onMediaCollectionItemClicked(item: MediaCollectionItem)
}