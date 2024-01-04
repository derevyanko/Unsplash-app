package com.derevianko.data.remote.api

import com.derevianko.data.remote.base.NetworkState
import com.derevianko.data.remote.dto.feed.PhotoFeedDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface UnsplashApiService {

    @GET("photos")
    suspend fun getPhotos(@Query("path") page: Int): Response<List<PhotoFeedDto>>
}