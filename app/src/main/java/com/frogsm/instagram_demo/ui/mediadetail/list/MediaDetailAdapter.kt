package com.frogsm.instagram_demo.ui.mediadetail.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SortedList
import androidx.recyclerview.widget.SortedListAdapterCallback
import javax.inject.Inject

class MediaDetailAdapter @Inject constructor(
) : RecyclerView.Adapter<MediaDetailViewHolder>() {

    private val mediaChildrenItems = SortedList(
        MediaChildrenItem::class.java,
        object : SortedListAdapterCallback<MediaChildrenItem>(this) {
            override fun areItemsTheSame(
                item1: MediaChildrenItem,
                item2: MediaChildrenItem
            ): Boolean = item1.index == item2.index

            override fun compare(
                o1: MediaChildrenItem,
                o2: MediaChildrenItem
            ): Int = o1.index - o2.index

            override fun areContentsTheSame(
                oldItem: MediaChildrenItem,
                newItem: MediaChildrenItem
            ): Boolean = oldItem == newItem
        }
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MediaDetailViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(viewType, parent, false)
        return MediaDetailViewHolder(view)
    }

    override fun getItemCount(): Int = mediaChildrenItems.size()

    override fun onBindViewHolder(holder: MediaDetailViewHolder, position: Int) {
        holder.onBind(mediaChildrenItems[position])
    }

    override fun getItemViewType(position: Int): Int = mediaChildrenItems[position].layoutId

    fun replaceData(items: List<MediaChildrenItem>) {
        mediaChildrenItems.replaceAll(items)
    }
}