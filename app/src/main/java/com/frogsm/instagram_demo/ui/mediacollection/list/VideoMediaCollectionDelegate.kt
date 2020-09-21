package com.frogsm.instagram_demo.ui.mediacollection.list

import android.view.View
import com.frogsm.instagram_demo.extensions.displayThumbnail
import com.frogsm.instagram_demo.ui.mediacollection.MediaCollectionController
import dagger.Lazy
import kotlinx.android.synthetic.main.item_media_collection_video.view.*
import javax.inject.Inject

interface VideoMediaCollectionDelegate {
    fun initUi(item: MediaCollectionItem.Video, view: View)
}

class VideoMediaCollectionDelegateImpl @Inject constructor(
    private val controller: Lazy<MediaCollectionController>
) : VideoMediaCollectionDelegate {

    override fun initUi(item: MediaCollectionItem.Video, view: View) {
        with(view) {
            image.displayThumbnail(item.thumbnailUrl) {
                centerCrop()
            }

            itemContainer.setOnClickListener {
                controller.get().onMediaCollectionItemClicked(item)
            }
        }
    }
}