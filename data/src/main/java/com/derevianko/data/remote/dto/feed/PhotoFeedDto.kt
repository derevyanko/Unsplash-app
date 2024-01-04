package com.derevianko.data.remote.dto.feed

data class PhotoFeedDto(
    val id: String,
    val description: String?,
    val urls: UrlsFeedDto,
    val user: UserFeedDto
)