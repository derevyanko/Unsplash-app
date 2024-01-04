package com.derevianko.unsplash.di.data

import com.derevianko.data.remote.api.UnsplashApiService
import com.derevianko.data.remote.base.ErrorHandlerImpl
import com.derevianko.data.remote.dataSource.UnsplashRemoteDataSource
import com.derevianko.data.remote.dataSource.UnsplashRemoteDataSourceImpl
import com.derevianko.data.repository.UnsplashRepositoryImpl
import com.derevianko.domain.base.ErrorHandler
import com.derevianko.domain.repository.UnsplashRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Provides
    @Singleton
    fun providesFlickrRepository(
        api: UnsplashRemoteDataSource,
        errorHandler: ErrorHandler
    ): UnsplashRepository = UnsplashRepositoryImpl(
        api = api,
        errorHandler = errorHandler
    )

    @Provides
    @Singleton
    fun providesFlickrRemoteRepository(unsplashApiService: UnsplashApiService): UnsplashRemoteDataSource =
        UnsplashRemoteDataSourceImpl(api = unsplashApiService)

    @Provides
    @Singleton
    fun providesErrorHandler(): ErrorHandler = ErrorHandlerImpl()
}