package com.derevianko.unsplash.presentation.view.feed

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.derevianko.domain.base.ErrorEntity
import com.derevianko.domain.base.State
import com.derevianko.domain.models.feed.PhotoFeed
import com.derevianko.unsplash.R
import com.derevianko.unsplash.databinding.FragmentPhotoFeedBinding
import com.derevianko.unsplash.presentation.view.details.PhotoDetailsFragment
import com.derevianko.unsplash.presentation.view.feed.adapter.PhotosAdapter
import com.derevianko.unsplash.presentation.viewmodel.PhotoFeedViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PhotoFeedFragment: Fragment(R.layout.fragment_photo_feed), PhotosAdapter.PhotoListener {

    private lateinit var binding: FragmentPhotoFeedBinding

    private val feedViewModel by activityViewModels<PhotoFeedViewModel>()

    private val photosAdapter = PhotosAdapter(listener = this)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentPhotoFeedBinding.bind(view)

        setupView()
        setupObservers()

        fetchRecentPhotos()
    }

    private fun setupView() = with (binding) {
        swipeRefreshLayout.setOnRefreshListener { fetchRecentPhotos() }

        setupRecyclerView()
    }

    private fun setupRecyclerView() = with (binding) {
        photosRecyclerView.layoutManager = LinearLayoutManager(context)
        photosRecyclerView.adapter = photosAdapter
    }

    private fun setupObservers() {
        observePhotos()
    }

    private fun observePhotos() {
        feedViewModel.photos.observe(viewLifecycleOwner) { state ->
            updateRecentPhotos(state)
        }
    }

    private fun updateRecentPhotos(state: State<List<PhotoFeed>>) = when (state) {
        is State.Success -> handleSuccessState(photos = state.data)
        is State.Failure -> handleFailureState(state)
        else -> {}
    }

    private fun handleSuccessState(photos: List<PhotoFeed>) = with (binding) {
        swipeRefreshLayout.isRefreshing = false

        photosRecyclerView.isVisible = true
        photosAdapter.updateDataSet(photos)
    }

    private fun handleFailureState(state: State.Failure<List<PhotoFeed>>) = with (binding) {
        swipeRefreshLayout.isRefreshing = false

        when (state.error) {
            is ErrorEntity.Network -> {
                photosRecyclerView.isVisible = false
                layoutError.isVisible = true
                buttonError.setOnClickListener { fetchRecentPhotos() }
            }
            else -> {
                photosRecyclerView.isVisible = false
                layoutError.rootView.isVisible = true
                buttonError.setOnClickListener { fetchRecentPhotos() }
            }
        }
    }

    private fun fetchRecentPhotos() {
        feedViewModel.getPhotos()
    }

    override fun onItemClick(position: Int) {
        val photos = (feedViewModel.photos.value as? State.Success)?.data ?: return
        val photoId = photos[position].url

        findNavController().navigate(
            R.id.action_photoFeedFragment_to_photoDetailsFragment,
            bundleOf(PhotoDetailsFragment.KEY_PHOTO_URL to photoId)
        )
    }
}