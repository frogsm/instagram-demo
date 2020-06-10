package com.frogsm.instagram_demo.ui.mediacollection.list

import android.view.View
import com.frogsm.instagram_demo.extensions.displayThumbnail
import com.frogsm.instagram_demo.ui.mediacollection.MediaCollectionController
import kotlinx.android.synthetic.main.item_media_collection_album.view.*
import javax.inject.Inject

interface AlbumMediaCollectionDelegate {
    fun initUi(item: MediaCollectionItem.Album, view: View)
}

class AlbumMediaCollectionDelegateImpl @Inject constructor(
    private val controller: MediaCollectionController
) : AlbumMediaCollectionDelegate {

    override fun initUi(item: MediaCollectionItem.Album, view: View) {
        with(view) {
            image.displayThumbnail(item.thumbnailUrl) {
                centerCrop()
            }

            itemContainer.setOnClickListener {
                controller.onMediaCollectionItemClicked(item)
            }
        }
    }
}