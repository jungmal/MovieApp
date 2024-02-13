package jungmal.movieapp.features.feed.data.mapper

import jungmal.movieapp.features.common.entity.CategoryEntity
import jungmal.movieapp.features.common.entity.EntityWrapper
import jungmal.movieapp.features.common.entity.MovieDetailEntity
import jungmal.movieapp.features.common.mapper.BaseMapper
import jungmal.movieapp.features.common.network.model.MovieResponse
import jungmal.movieapp.features.feed.data.FeedConstants
import jungmal.movieapp.features.feed.domain.enums.SortOrder
import jungmal.movieapp.library.storage.Storage
import javax.inject.Inject

class CategoryMapper @Inject constructor(
    private val storage: Storage
) : BaseMapper<List<MovieResponse>, List<CategoryEntity>>() {
    override fun getSuccess(
        model: List<MovieResponse>?,
        extra: Any?
    ): EntityWrapper.Success<List<CategoryEntity>> {
        return model?.let {
            EntityWrapper.Success(
                entity = mutableListOf<MovieDetailEntity>()
                    .apply {
                        addAll(model.map { it.toMovieDetailEntity() })
                    }
                    .also {
                        storage.save(FeedConstants.MOVIE_LIST_KEY, it)
                    }
                    .map {
                        it.toMovieThumbnailEntity()
                    }
                    .toCategoryList(if (extra is SortOrder) extra else SortOrder.RATING)
            )
        } ?: EntityWrapper.Success(
            entity = listOf()
        )
    }

    override fun getFailure(error: Throwable): EntityWrapper.Fail<List<CategoryEntity>> {
        return EntityWrapper.Fail(error)
    }
}