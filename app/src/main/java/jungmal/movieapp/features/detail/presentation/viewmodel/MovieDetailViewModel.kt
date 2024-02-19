package jungmal.movieapp.features.detail.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import jungmal.movieapp.features.detail.domain.usecase.GetMovieDetailUseCase
import jungmal.movieapp.features.detail.presentation.input.DetailViewModelInputs
import jungmal.movieapp.features.detail.presentation.output.DetailUiEffect
import jungmal.movieapp.features.detail.presentation.output.DetailViewModelOutputs
import jungmal.movieapp.features.detail.presentation.output.MovieDetailState
import kotlinx.coroutines.flow.collectLatest
import javax.inject.Inject


@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    private val getMovieDetailUseCase: GetMovieDetailUseCase
) : ViewModel(), DetailViewModelInputs, DetailViewModelOutputs {

    val inputs: DetailViewModelInputs = this
    val outputs: DetailViewModelOutputs = this

    private val _detailState: MutableStateFlow<MovieDetailState> =
        MutableStateFlow(MovieDetailState.Initial)
    override val detailState: StateFlow<MovieDetailState>
        get() = _detailState

    private val _detailUiEffect = MutableSharedFlow<DetailUiEffect>(replay = 0)
    override val detailUiEffect: SharedFlow<DetailUiEffect>
        get() = _detailUiEffect

    var navigateUp: (() -> Unit)? = null

    init {
        viewModelScope.launch {
            outputs.detailUiEffect.collectLatest {
                when (it) {
                    is DetailUiEffect.Back -> {
                        navigateUp?.invoke()
                    }

                    is DetailUiEffect.OpenUrl -> {

                    }

                    is DetailUiEffect.RateMovie -> {

                    }
                }
            }
        }
    }

    suspend fun initMovieName(movieName: String) {
        _detailState.value = MovieDetailState.Main(
            movieDetailEntity = getMovieDetailUseCase(movieName)
        )
    }

    override fun goBackToFeed() {
        viewModelScope.launch {
            _detailUiEffect.emit(
                DetailUiEffect.Back
            )
        }
    }

    override fun openImdbClicked() {
        viewModelScope.launch {
            if (detailState.value is MovieDetailState.Main) {
                val value = detailState.value as MovieDetailState.Main
                _detailUiEffect.emit(
                    DetailUiEffect.OpenUrl(
                        value.movieDetailEntity.imdbPath
                    )
                )
            }
        }
    }

    override fun rateClicked() {
        viewModelScope.launch {
            if (detailState.value is MovieDetailState.Main) {
                val value = detailState.value as MovieDetailState.Main
                _detailUiEffect.emit(
                    DetailUiEffect.RateMovie(
                        movieTitle = value.movieDetailEntity.title,
                        rating = value.movieDetailEntity.rating
                    )
                )
            }
        }
    }
}
