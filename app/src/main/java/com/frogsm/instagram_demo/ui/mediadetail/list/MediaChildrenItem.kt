package com.frogsm.instagram_demo.ui.mediadetail.list

import android.view.View
import androidx.annotation.LayoutRes
import com.frogsm.instagram_demo.R

sealed class MediaChildrenItem(
    open val index: Int,
    open val mediaUrl: String,
    @LayoutRes open val layoutId: Int
) : Presenter {

    data class Image(
        override val index: Int,
        override val mediaUrl: String,
        override val layoutId: Int = R.layout.item_media_detail_image
    ) : MediaChildrenItem(index, mediaUrl, layoutId) {

        override fun presentUi(delegate: MediaDetailDelegate, view: View) {
            delegate.initUi(this, view)
        }

        override fun recycleUi(delegate: MediaDetailDelegate, view: View) {
            delegate.recycleUi(this, view)
        }
    }

    data class Video(
        override val index: Int,
        override val mediaUrl: String,
        override val layoutId: Int = R.layout.item_media_detail_video
    ) : MediaChildrenItem(index, mediaUrl, layoutId) {

        override fun presentUi(delegate: MediaDetailDelegate, view: View) {
            delegate.initUi(this, view)
        }

        override fun recycleUi(delegate: MediaDetailDelegate, view: View) {
            delegate.recycleUi(this, view)
        }
    }
}

interface Presenter {
    fun presentUi(delegate: MediaDetailDelegate, view: View)
    fun recycleUi(delegate: MediaDetailDelegate, view: View)
}