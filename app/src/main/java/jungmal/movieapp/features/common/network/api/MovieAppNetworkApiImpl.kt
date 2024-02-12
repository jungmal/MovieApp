package jungmal.movieapp.features.common.network.api

import com.google.gson.reflect.TypeToken
import jungmal.movieapp.features.common.network.model.MovieResponse
import jungmal.movieapp.library.network.model.ApiResult
import jungmal.movieapp.library.network.retrofit.NetworkRequestFactory
import javax.inject.Inject

class MovieAppNetworkApiImpl @Inject constructor(
    private val networkRequestFactory: NetworkRequestFactory
): MovieAppNetworkApi {
    override suspend fun getMovies(): ApiResult<List<MovieResponse>> {
        return networkRequestFactory.create(
            url = "top250.json",
            type = object : TypeToken<List<MovieResponse>>(){}.type
        )
    }
}