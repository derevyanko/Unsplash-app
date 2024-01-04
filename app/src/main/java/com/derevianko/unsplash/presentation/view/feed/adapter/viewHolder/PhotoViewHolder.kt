package com.derevianko.unsplash.presentation.view.feed.adapter.viewHolder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.derevianko.domain.models.feed.PhotoFeed
import com.derevianko.unsplash.R
import com.derevianko.unsplash.databinding.ItemPhotoBinding
import com.derevianko.unsplash.presentation.view.feed.adapter.PhotosAdapter

class PhotoViewHolder(
    itemView: View,
    private val listener: PhotosAdapter.PhotoListener
): RecyclerView.ViewHolder(itemView) {

    private val binding = ItemPhotoBinding.bind(itemView)

    fun bind(item: PhotoFeed, position: Int) = with(binding) {
        bindUrl(item.url)
        bindAuthor(item.authorName)
        bindDescription(item.description)

        root.setOnClickListener {
            listener.onItemClick(position)
        }
    }

    private fun bindUrl(url: String) = with (binding) {
        Glide.with(itemView.context)
            .load(url)
            .placeholder(R.drawable.ic_placeholder)
            .error(R.drawable.ic_placeholder)
            .centerCrop()
            .into(imageView)
    }

    private fun bindAuthor(authorName: String) = with (binding) {
        author.text = authorName
    }

    private fun bindDescription(title: String) = with (binding) {
        description.text = title
    }
}