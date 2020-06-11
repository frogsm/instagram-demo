package com.frogsm.instagram_demo.ui.mediadetail.detail

import com.frogsm.instagram_demo.ui.mediadetail.list.MediaChildrenItem

data class MediaDetailItem(
    val id: String,
    val userName: String,
    val summaryCaption: String,
    val extraCaption: String,
    val timeStamp: String,
    val children: List<MediaChildrenItem>
) {
    companion object {
        val EMPTY = MediaDetailItem("", "", "", "", "", emptyList())
    }
}