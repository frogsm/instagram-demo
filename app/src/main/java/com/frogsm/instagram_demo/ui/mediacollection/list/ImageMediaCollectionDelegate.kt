package com.frogsm.instagram_demo.ui.mediacollection.list

import android.view.View
import javax.inject.Inject

interface ImageMediaCollectionDelegate {
    fun initUi(item: MediaCollectionItem.Image, view: View)
}

class ImageMediaCollectionDelegateImpl @Inject constructor(
) : ImageMediaCollectionDelegate {

    override fun initUi(item: MediaCollectionItem.Image, view: View) {

    }
}