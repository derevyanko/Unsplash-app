package com.derevianko.unsplash.presentation.view.feed.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.derevianko.domain.models.feed.PhotoFeed
import com.derevianko.unsplash.R
import com.derevianko.unsplash.presentation.view.feed.adapter.viewHolder.PhotoViewHolder

class PhotosAdapter(
    private var items: List<PhotoFeed> = emptyList(),
    private val listener: PhotoListener
): RecyclerView.Adapter<PhotoViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder {
        val inflater = LayoutInflater.from(parent.context).inflate(R.layout.item_photo, parent, false)
        return PhotoViewHolder(
            itemView = inflater,
            listener = listener
        )
    }

    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
        holder.bind(
            item = items[position],
            position = position
        )
    }

    override fun getItemCount() = items.size

    fun updateDataSet(items: List<PhotoFeed>) {
        this.items = items
        notifyDataSetChanged()
    }

    interface PhotoListener {

        fun onItemClick(position: Int)
    }
}