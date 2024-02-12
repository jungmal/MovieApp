package jungmal.movieapp.features.common.repository

import android.util.Log
import jungmal.movieapp.features.common.network.api.MovieAppNetworkApi
import jungmal.movieapp.library.network.model.ApiResponse
import javax.inject.Inject

class MovieRepository @Inject constructor(
    private val movieNetworkApi: MovieAppNetworkApi
): MovieDataSource {
    override suspend fun getMovieList() {
        val data = movieNetworkApi.getMovies()
        if (data.response is ApiResponse.Success) {
            Log.d("MovieRepository", data.response.data.toString())
        } else {
            Log.d("MovieRepository", data.response.toString())
        }
    }
}