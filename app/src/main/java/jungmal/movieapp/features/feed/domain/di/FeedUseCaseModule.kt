package jungmal.movieapp.features.feed.domain.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import jungmal.movieapp.features.feed.domain.usecase.GetFeedCategoryUseCase
import jungmal.movieapp.features.feed.domain.usecase.GetFeedCategoryUseCaseImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class FeedUseCaseModule {

    @Singleton
    @Binds
    abstract fun bindGetMovieListUseCase(getMovieListUseCase: GetFeedCategoryUseCaseImpl): GetFeedCategoryUseCase
}