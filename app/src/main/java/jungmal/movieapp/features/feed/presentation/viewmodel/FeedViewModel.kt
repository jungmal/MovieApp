package jungmal.movieapp.features.feed.presentation.viewmodel

import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import jungmal.movieapp.features.common.entity.EntityWrapper
import jungmal.movieapp.features.feed.domain.usecase.GetFeedCategoryUseCase
import jungmal.movieapp.features.feed.presentation.input.FeedViewModelInput
import jungmal.movieapp.features.feed.presentation.output.FeedState
import jungmal.movieapp.features.feed.presentation.output.FeedUiEffect
import jungmal.movieapp.features.feed.presentation.output.FeedViewModelOutput
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FeedViewModel @Inject constructor(
    private val getFeedCategoryUseCase: GetFeedCategoryUseCase
): ViewModel(), FeedViewModelInput, FeedViewModelOutput {

    val output: FeedViewModelOutput = this
    val input: FeedViewModelInput = this

    private val _feedState: MutableStateFlow<FeedState> = MutableStateFlow(FeedState.Loading)
    override val feedState: StateFlow<FeedState> = _feedState

    private val _feedUiEffect = MutableSharedFlow<FeedUiEffect>(replay = 0)
    override val feedUiEffect:SharedFlow<FeedUiEffect> = _feedUiEffect

    var navigateTo: ((String) -> Unit)? = null

    init {
        fetchFeed()

        viewModelScope.launch {
            feedUiEffect.collectLatest {
                when (it) {
                    is FeedUiEffect.OpenMovieDetail -> {
                        navigateTo?.let { navigateTo -> navigateTo(it.movieName) }
                    }
                    else -> {

                    }
                }
            }
        }
    }

    private fun fetchFeed() {
        viewModelScope.launch {
            _feedState.value = FeedState.Loading

            val categories = getFeedCategoryUseCase()
            _feedState.value = when(categories) {
                is EntityWrapper.Success -> {
                    FeedState.Main(
                        categories = categories.entity
                    )
                }
                is EntityWrapper.Fail -> {
                    FeedState.Failed(
                        reason = categories.error.message ?: "Unexpected error"
                    )
                }
            }
        }
    }

    override fun openDetail(movieName: String) {
        viewModelScope.launch {
            _feedUiEffect.emit(
                FeedUiEffect.OpenMovieDetail(movieName)
            )
        }
    }

    override fun openInfoDialog() {

    }

    override fun refreshFeed() {

    }
}