package com.frogsm.instagram_demo.ui.mediadetail.list

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.frogsm.instagram_demo.extensions.displayImage
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_media_detail.view.*
import javax.inject.Inject

class MediaDetailViewHolder @Inject constructor(
    override val containerView: View
) : RecyclerView.ViewHolder(containerView), LayoutContainer {

    fun onBind(item: MediaChildrenItem) {
        with(containerView) {
            media.displayImage(item.mediaUrl)
        }
    }
}