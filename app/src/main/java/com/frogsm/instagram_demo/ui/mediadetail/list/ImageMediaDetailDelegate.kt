package com.frogsm.instagram_demo.ui.mediadetail.list

import android.view.View
import com.frogsm.instagram_demo.extensions.displayImage
import kotlinx.android.synthetic.main.item_media_detail_image.view.*
import javax.inject.Inject

interface ImageMediaDetailDelegate {
    fun initUi(item: MediaChildrenItem.Image, view: View)
}

class ImageMediaDetailDelegateImpl @Inject constructor(
) : ImageMediaDetailDelegate {

    override fun initUi(item: MediaChildrenItem.Image, view: View) {
        with(view) {
            media.displayImage(item.mediaUrl)
        }
    }
}