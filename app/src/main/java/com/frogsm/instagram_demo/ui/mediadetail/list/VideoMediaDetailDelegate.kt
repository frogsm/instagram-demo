package com.frogsm.instagram_demo.ui.mediadetail.list

import android.view.View
import javax.inject.Inject

interface VideoMediaDetailDelegate {
    fun initUi(item: MediaChildrenItem.Video, view: View)
}

class VideoMediaDetailDelegateImpl @Inject constructor(
) : VideoMediaDetailDelegate {

    override fun initUi(item: MediaChildrenItem.Video, view: View) {

    }
}