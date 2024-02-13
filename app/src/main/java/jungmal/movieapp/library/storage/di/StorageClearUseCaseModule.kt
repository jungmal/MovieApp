package jungmal.movieapp.library.storage.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import jungmal.movieapp.library.storage.usecases.StorageClearUseCase
import jungmal.movieapp.library.storage.usecases.StorageClearUseCaseImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class StorageClearUseCaseModule {

    @Binds
    @Singleton
    abstract fun bindStorageClearUseCase(
        storageClearUserCase: StorageClearUseCaseImpl
    ): StorageClearUseCase
}
