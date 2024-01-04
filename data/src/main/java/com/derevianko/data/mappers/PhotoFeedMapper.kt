package com.derevianko.data.mappers

import com.derevianko.data.remote.dto.feed.PhotoFeedDto
import com.derevianko.domain.models.feed.PhotoFeed

object PhotoFeedMapper {

    fun fromDtoToUi(from: List<PhotoFeedDto>) = from.map { it.fromDtoToUi() }

    private fun PhotoFeedDto.fromDtoToUi() = PhotoFeed(
        id = this.id,
        description = this.description ?: "",
        url = this.urls.regular,
        authorName = this.user.name
    )
}