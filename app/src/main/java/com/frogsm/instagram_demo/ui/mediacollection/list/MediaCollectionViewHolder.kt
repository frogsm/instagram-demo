package com.frogsm.instagram_demo.ui.mediacollection.list

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.extensions.LayoutContainer

class MediaCollectionViewHolder(
    override val containerView: View
) : RecyclerView.ViewHolder(containerView), LayoutContainer {

    fun onBind(delegate: MediaCollectionDelegate, item: MediaCollectionItem) {
        item.presentUi(delegate, containerView)
    }
}