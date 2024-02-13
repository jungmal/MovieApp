package jungmal.movieapp.features.feed.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import jungmal.movieapp.features.common.repository.MovieDataSource
import jungmal.movieapp.features.feed.presentation.input.FeedViewModelInput
import jungmal.movieapp.features.feed.presentation.output.FeedState
import jungmal.movieapp.features.feed.presentation.output.FeedUiEffect
import jungmal.movieapp.features.feed.presentation.output.FeedViewModelOutput
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FeedViewModel @Inject constructor(
    movieDataSource: MovieDataSource
): ViewModel(), FeedViewModelInput, FeedViewModelOutput {

    private val _feedState: MutableStateFlow<FeedState> = MutableStateFlow(FeedState.Loading)
    override val feedState: StateFlow<FeedState> = _feedState

    private val _feedUiEffect = MutableSharedFlow<FeedUiEffect>(replay = 0)
    override val feedUiEffect:SharedFlow<FeedUiEffect> = _feedUiEffect

    init {
        viewModelScope.launch {
            movieDataSource.getMovieList()
        }
    }

    override fun openDetail(movieName: String) {
        TODO("Not yet implemented")
    }

    override fun openInfoDialog() {
        TODO("Not yet implemented")
    }

    override fun refreshFeed() {
        TODO("Not yet implemented")
    }
}