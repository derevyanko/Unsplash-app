package com.derevianko.unsplash.presentation.view.details

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.derevianko.unsplash.R
import com.derevianko.unsplash.databinding.FragmentPhotoDetailsBinding

class PhotoDetailsFragment: Fragment(R.layout.fragment_photo_details) {

    private lateinit var binding: FragmentPhotoDetailsBinding

    private val photoUrl: String
        get() = arguments?.getString(KEY_PHOTO_URL) ?: ""

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentPhotoDetailsBinding.bind(view)

        bindPhoto()
    }

    private fun bindPhoto() {
        context?.let {
            Glide.with(it)
                .load(photoUrl)
                .placeholder(R.drawable.ic_placeholder)
                .error(R.drawable.ic_placeholder)
                .into(binding.imageView)
        }
    }

    companion object {

        const val KEY_PHOTO_URL = "PHOTO_URL"
    }
}