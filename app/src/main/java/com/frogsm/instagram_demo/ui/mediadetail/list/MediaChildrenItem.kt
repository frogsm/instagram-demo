package com.frogsm.instagram_demo.ui.mediadetail.list

import androidx.annotation.LayoutRes
import com.frogsm.instagram_demo.R

data class MediaChildrenItem(
    val index: Int,
    val mediaUrl: String,
    @LayoutRes val layoutId: Int = R.layout.item_media_detail
)