package com.frogsm.instagram_demo.ui.mediadetail.list

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.extensions.LayoutContainer
import javax.inject.Inject

class MediaDetailViewHolder @Inject constructor(
    override val containerView: View
) : RecyclerView.ViewHolder(containerView), LayoutContainer {

    fun onBind(delegate: MediaDetailDelegate, item: MediaChildrenItem) {
        item.presentUi(delegate, containerView)
    }
}