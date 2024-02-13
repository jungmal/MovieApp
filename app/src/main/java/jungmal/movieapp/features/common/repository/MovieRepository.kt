package jungmal.movieapp.features.common.repository

import android.util.Log
import jungmal.movieapp.features.common.entity.CategoryEntity
import jungmal.movieapp.features.common.entity.EntityWrapper
import jungmal.movieapp.features.common.entity.MovieDetailEntity
import jungmal.movieapp.features.common.network.api.MovieAppNetworkApi
import jungmal.movieapp.features.feed.data.FeedConstants
import jungmal.movieapp.features.feed.data.mapper.CategoryMapper
import jungmal.movieapp.features.feed.domain.enums.SortOrder
import jungmal.movieapp.library.network.model.ApiResponse
import jungmal.movieapp.library.storage.Storage
import javax.inject.Inject

class MovieRepository @Inject constructor(
    private val movieNetworkApi: MovieAppNetworkApi,
    private val storage: Storage,
    private val categoryMapper: CategoryMapper
): MovieDataSource {
    override suspend fun getCategories(sortOrder: SortOrder?): EntityWrapper<List<CategoryEntity>> {
        return categoryMapper.mapFromResult(
            result = movieNetworkApi.getMovies(),
            extra = sortOrder
        )
    }

    override suspend fun getMovieDetail(movieName: String): MovieDetailEntity {
        return storage
            .get<List<MovieDetailEntity>>(FeedConstants.MOVIE_LIST_KEY)
            ?.single { it.title == movieName }
            ?: MovieDetailEntity()
    }
}