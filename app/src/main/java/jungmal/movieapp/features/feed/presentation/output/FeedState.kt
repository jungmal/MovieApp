package jungmal.movieapp.features.feed.presentation.output

import jungmal.movieapp.features.common.entity.CategoryEntity

sealed class FeedState {
    object Loading: FeedState()
    class Main(
        val categories: List<CategoryEntity>
    ): FeedState()
    class Failed(
        val reason: String
    ): FeedState()
}