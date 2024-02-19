package jungmal.movieapp.features.detail.presentation.output

import jungmal.movieapp.features.common.entity.MovieDetailEntity

sealed class MovieDetailState {
    object Initial : MovieDetailState()
    class Main(val movieDetailEntity: MovieDetailEntity) : MovieDetailState()
}
