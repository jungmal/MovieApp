package jungmal.movieapp.features.feed.domain.usecase

import jungmal.movieapp.features.common.entity.CategoryEntity
import jungmal.movieapp.features.common.entity.EntityWrapper
import jungmal.movieapp.features.feed.domain.enums.SortOrder

interface GetFeedCategoryUseCase {
    suspend operator fun invoke(sortOrder: SortOrder? = null): EntityWrapper<List<CategoryEntity>>
}