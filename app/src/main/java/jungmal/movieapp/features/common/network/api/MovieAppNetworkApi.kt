package jungmal.movieapp.features.common.network.api

import jungmal.movieapp.features.common.network.model.MovieResponse
import jungmal.movieapp.library.network.model.ApiResult

interface MovieAppNetworkApi {
    suspend fun getMovies(): ApiResult<List<MovieResponse>>
}