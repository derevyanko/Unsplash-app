package com.derevianko.unsplash.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.derevianko.domain.base.State
import com.derevianko.domain.models.feed.PhotoFeed
import com.derevianko.domain.repository.UnsplashRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

@HiltViewModel
class PhotoFeedViewModel @Inject constructor(
    private val repository: UnsplashRepository,
    coroutineContext: CoroutineContext
): ViewModel() {

    private val scope = CoroutineScope(coroutineContext)

    private val _photos = MutableLiveData<State<List<PhotoFeed>>>()
    val photos = _photos

    fun getPhotos(page: Int = 1) = scope.launch {
        val photos = repository.getPhotos(page)
        _photos.postValue(photos)
    }
}