package com.derevianko.domain.repository

import com.derevianko.domain.base.State
import com.derevianko.domain.models.feed.PhotoFeed

interface UnsplashRepository {

    suspend fun getPhotos(page: Int): State<List<PhotoFeed>>
}