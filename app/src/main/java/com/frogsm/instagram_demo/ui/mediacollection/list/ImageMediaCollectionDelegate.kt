package com.frogsm.instagram_demo.ui.mediacollection.list

import android.view.View
import com.frogsm.instagram_demo.extensions.displayThumbnail
import kotlinx.android.synthetic.main.item_media_collection_image.view.*
import javax.inject.Inject

interface ImageMediaCollectionDelegate {
    fun initUi(item: MediaCollectionItem.Image, view: View)
}

class ImageMediaCollectionDelegateImpl @Inject constructor(
) : ImageMediaCollectionDelegate {

    override fun initUi(item: MediaCollectionItem.Image, view: View) {
        with(view) {
            image.displayThumbnail(item.thumbnailUrl) {
                centerCrop()
            }
        }
    }
}