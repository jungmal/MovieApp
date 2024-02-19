package jungmal.movieapp.features.detail.domain.usecase

import jungmal.movieapp.features.common.entity.MovieDetailEntity
import jungmal.movieapp.features.common.repository.MovieDataSource
import javax.inject.Inject

class GetMovieDetailUseCaseImpl @Inject constructor(
    private val dataSource: MovieDataSource
) : GetMovieDetailUseCase {
    override suspend fun invoke(name: String): MovieDetailEntity {
        return dataSource.getMovieDetail(name)
    }
}
