package com.derevianko.data.remote.dataSource

import com.derevianko.data.remote.api.UnsplashApiService
import com.derevianko.data.remote.base.BaseRemoteDataSource
import javax.inject.Inject

class UnsplashRemoteDataSourceImpl @Inject constructor(
    private val api: UnsplashApiService
): BaseRemoteDataSource(), UnsplashRemoteDataSource {

    override suspend fun getPhotos(page: Int) = request { api.getPhotos(page) }
}