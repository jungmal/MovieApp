package jungmal.movieapp.features.detail.domain.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import jungmal.movieapp.features.detail.domain.usecase.GetMovieDetailUseCaseImpl
import jungmal.movieapp.features.detail.domain.usecase.GetMovieDetailUseCase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DetailUseCaseModule {

    @Singleton
    @Binds
    abstract fun bindGetMovieDetailUseCase(getMovieDetailUseCase: GetMovieDetailUseCaseImpl): GetMovieDetailUseCase
}
