package com.derevianko.data.remote.dataSource

import com.derevianko.data.remote.base.NetworkState
import com.derevianko.data.remote.dto.feed.PhotoFeedDto

interface UnsplashRemoteDataSource {

    suspend fun getPhotos(page: Int): NetworkState<List<PhotoFeedDto>>
}