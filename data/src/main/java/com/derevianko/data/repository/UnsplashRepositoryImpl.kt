package com.derevianko.data.repository

import com.derevianko.data.mappers.PhotoFeedMapper
import com.derevianko.data.remote.base.BaseRemoteDataSource
import com.derevianko.data.remote.base.NetworkState
import com.derevianko.data.remote.dataSource.UnsplashRemoteDataSource
import com.derevianko.domain.base.ErrorEntity
import com.derevianko.domain.base.ErrorHandler
import com.derevianko.domain.base.State
import com.derevianko.domain.models.feed.PhotoFeed
import com.derevianko.domain.repository.UnsplashRepository
import javax.inject.Inject

class UnsplashRepositoryImpl @Inject constructor(
    private val api: UnsplashRemoteDataSource,
    private val errorHandler: ErrorHandler
): BaseRemoteDataSource(), UnsplashRepository {

    override suspend fun getPhotos(page: Int): State<List<PhotoFeed>> {
        return when(val response = api.getPhotos(page) ) {
            is NetworkState.Success -> {
                val photosDto = response.data ?: return State.Failure(error = ErrorEntity.Unknown)
                val uiModels = PhotoFeedMapper.fromDtoToUi(from = photosDto)
                State.Success(uiModels)
            }
            is NetworkState.Failure -> State.Failure(error = errorHandler.getError(response.statusCode))
            else -> State.Failure(error = ErrorEntity.Unknown)
        }
    }
}