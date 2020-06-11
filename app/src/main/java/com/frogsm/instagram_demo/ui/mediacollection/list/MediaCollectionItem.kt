package com.frogsm.instagram_demo.ui.mediacollection.list

import android.view.View
import androidx.annotation.LayoutRes
import com.frogsm.instagram_demo.R

sealed class MediaCollectionItem(
    open val index: Int,
    open val mediaId: String,
    open val thumbnailUrl: String,
    @LayoutRes open val layoutId: Int
) : Presenter {

    data class Image(
        override val index: Int,
        override val mediaId: String,
        override val thumbnailUrl: String,
        override val layoutId: Int = R.layout.item_media_collection_image
    ) : MediaCollectionItem(index, mediaId, thumbnailUrl, layoutId) {

        override fun presentUi(delegate: MediaCollectionDelegate, view: View) {
            delegate.initUi(this, view)
        }
    }

    data class Video(
        override val index: Int,
        override val mediaId: String,
        override val thumbnailUrl: String,
        override val layoutId: Int = R.layout.item_media_collection_video
    ) : MediaCollectionItem(index, mediaId, thumbnailUrl, layoutId) {

        override fun presentUi(delegate: MediaCollectionDelegate, view: View) {
            delegate.initUi(this, view)
        }
    }

    data class Album(
        override val index: Int,
        override val mediaId: String,
        override val thumbnailUrl: String,
        override val layoutId: Int = R.layout.item_media_collection_album
    ) : MediaCollectionItem(index, mediaId, thumbnailUrl, layoutId) {

        override fun presentUi(delegate: MediaCollectionDelegate, view: View) {
            delegate.initUi(this, view)
        }
    }
}

interface Presenter {
    fun presentUi(delegate: MediaCollectionDelegate, view: View)
}