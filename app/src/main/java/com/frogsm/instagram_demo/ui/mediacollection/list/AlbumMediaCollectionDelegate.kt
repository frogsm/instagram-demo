package com.frogsm.instagram_demo.ui.mediacollection.list

import android.view.View
import javax.inject.Inject

interface AlbumMediaCollectionDelegate {
    fun initUi(item: MediaCollectionItem.Album, view: View)
}

class AlbumMediaCollectionDelegateImpl @Inject constructor(
) : AlbumMediaCollectionDelegate {

    override fun initUi(item: MediaCollectionItem.Album, view: View) {

    }
}