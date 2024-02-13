package jungmal.movieapp.features.feed.presentation.output

import jungmal.movieapp.features.common.entity.MovieFeedItemEntity

sealed class FeedState {
    object Loading: FeedState()
    class Main(
        val movieList: List<MovieFeedItemEntity>
    ): FeedState()
    class Failed(
        val reason: String
    ): FeedState()
}