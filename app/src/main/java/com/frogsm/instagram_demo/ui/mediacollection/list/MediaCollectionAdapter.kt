package com.frogsm.instagram_demo.ui.mediacollection.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SortedList
import androidx.recyclerview.widget.SortedListAdapterCallback
import javax.inject.Inject

class MediaCollectionAdapter @Inject constructor(
    private val delegate: MediaCollectionDelegate
) : RecyclerView.Adapter<MediaCollectionViewHolder>() {

    private val mediaCollectionItems = SortedList(
        MediaCollectionItem::class.java,
        object : SortedListAdapterCallback<MediaCollectionItem>(this) {
            override fun areItemsTheSame(
                item1: MediaCollectionItem,
                item2: MediaCollectionItem
            ): Boolean = item1.index == item2.index

            override fun compare(
                o1: MediaCollectionItem,
                o2: MediaCollectionItem
            ): Int = o1.index - o2.index

            override fun areContentsTheSame(
                oldItem: MediaCollectionItem,
                newItem: MediaCollectionItem
            ): Boolean = oldItem == newItem
        }
    )


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MediaCollectionViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(viewType, parent, false)
        return MediaCollectionViewHolder(view)
    }

    override fun getItemCount(): Int = mediaCollectionItems.size()

    override fun onBindViewHolder(holder: MediaCollectionViewHolder, position: Int) {
        holder.onBind(delegate, mediaCollectionItems[position])
    }

    override fun getItemViewType(position: Int): Int = mediaCollectionItems[position].layoutId

    fun replaceData(items: List<MediaCollectionItem>) {
        mediaCollectionItems.replaceAll(items)
    }
}