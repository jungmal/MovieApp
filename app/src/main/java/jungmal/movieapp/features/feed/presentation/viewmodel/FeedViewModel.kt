package jungmal.movieapp.features.feed.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import jungmal.movieapp.features.common.repository.MovieDataSource
import jungmal.movieapp.features.common.repository.MovieRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FeedViewModel @Inject constructor(
    movieDataSource: MovieDataSource
): ViewModel() {
    init {
        viewModelScope.launch {
            movieDataSource.getMovieList()
        }
    }
}