package com.frogsm.instagram_demo.ui.mediadetail.list

import android.view.View
import com.frogsm.instagram_demo.extensions.displayThumbnail
import kotlinx.android.synthetic.main.item_media_detail_image.view.*
import javax.inject.Inject

interface ImageMediaDetailDelegate {
    fun initUi(item: MediaChildrenItem.Image, view: View)
    fun recycleUi(item: MediaChildrenItem.Image, view: View)
}

class ImageMediaDetailDelegateImpl @Inject constructor(
) : ImageMediaDetailDelegate {

    override fun initUi(item: MediaChildrenItem.Image, view: View) {
        with(view) {
            media.displayThumbnail(item.mediaUrl)
        }
    }

    override fun recycleUi(item: MediaChildrenItem.Image, view: View) {

    }
}