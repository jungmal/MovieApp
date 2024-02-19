package jungmal.movieapp.features.detail.domain.usecase

import jungmal.movieapp.features.common.entity.MovieDetailEntity

interface GetMovieDetailUseCase {
    suspend operator fun invoke(name: String): MovieDetailEntity
}
