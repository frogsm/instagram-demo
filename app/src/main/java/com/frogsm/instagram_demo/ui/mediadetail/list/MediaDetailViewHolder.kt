package com.frogsm.instagram_demo.ui.mediadetail.list

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.extensions.LayoutContainer
import javax.inject.Inject

class MediaDetailViewHolder @Inject constructor(
    override val containerView: View
) : RecyclerView.ViewHolder(containerView), LayoutContainer {

    private var item: MediaChildrenItem? = null

    fun onBind(delegate: MediaDetailDelegate, item: MediaChildrenItem) {
        this.item = item
        item.presentUi(delegate, containerView)
    }

    fun onRecycled(delegate: MediaDetailDelegate) {
        item?.recycleUi(delegate, containerView)
    }
}