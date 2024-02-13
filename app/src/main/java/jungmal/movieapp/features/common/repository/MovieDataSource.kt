package jungmal.movieapp.features.common.repository

import jungmal.movieapp.features.common.entity.CategoryEntity
import jungmal.movieapp.features.common.entity.EntityWrapper
import jungmal.movieapp.features.common.entity.MovieDetailEntity
import jungmal.movieapp.features.feed.domain.enums.SortOrder

interface MovieDataSource {
    suspend fun getCategories(sortOrder: SortOrder? = null): EntityWrapper<List<CategoryEntity>>
    suspend fun getMovieDetail(movieName: String): MovieDetailEntity
}