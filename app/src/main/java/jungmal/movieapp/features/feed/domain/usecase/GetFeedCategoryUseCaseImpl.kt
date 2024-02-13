package jungmal.movieapp.features.feed.domain.usecase

import jungmal.movieapp.features.common.entity.CategoryEntity
import jungmal.movieapp.features.common.entity.EntityWrapper
import jungmal.movieapp.features.common.repository.MovieDataSource
import jungmal.movieapp.features.feed.domain.enums.SortOrder
import javax.inject.Inject

class GetFeedCategoryUseCaseImpl @Inject constructor(
    private val dataSource: MovieDataSource
): GetFeedCategoryUseCase {
    override suspend fun invoke(sortOrder: SortOrder?): EntityWrapper<List<CategoryEntity>> {
        return dataSource.getCategories(sortOrder)
    }
}