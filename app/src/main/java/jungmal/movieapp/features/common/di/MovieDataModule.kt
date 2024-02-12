package jungmal.movieapp.features.common.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import jungmal.movieapp.features.common.network.api.MovieAppNetworkApiImpl
import jungmal.movieapp.features.common.network.api.MovieAppNetworkApi
import jungmal.movieapp.features.common.repository.MovieDataSource
import jungmal.movieapp.features.common.repository.MovieRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class MovieDataModule {

    @Singleton
    @Binds
    abstract fun bindMovieRepository(movieRepository: MovieRepository): MovieDataSource

    @Singleton
    @Binds
    abstract fun bindNetwork(networkApi: MovieAppNetworkApiImpl): MovieAppNetworkApi

}