package jungmal.movieapp.features.common.repository

interface MovieDataSource {
    suspend fun getMovieList()
}