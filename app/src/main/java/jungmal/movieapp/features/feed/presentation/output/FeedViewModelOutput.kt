package jungmal.movieapp.features.feed.presentation.output

import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow

interface FeedViewModelOutput {
    val feedState: StateFlow<FeedState>
    val feedUiEffect: SharedFlow<FeedUiEffect>
}

sealed class FeedUiEffect {
    data class OpenMovieDetail(val movieName: String): FeedUiEffect()
    object OpenInfoDialog: FeedUiEffect()
}