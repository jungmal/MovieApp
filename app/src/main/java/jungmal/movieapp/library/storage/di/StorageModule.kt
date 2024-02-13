package jungmal.movieapp.library.storage.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import jungmal.movieapp.library.storage.Storage
import jungmal.movieapp.library.storage.StorageManager
import jungmal.movieapp.library.storage.helpers.DataConverter
import jungmal.movieapp.library.storage.helpers.DataEncoding
import jungmal.movieapp.library.storage.prefs.SharedPrefsStorageProvider
import jungmal.movieapp.library.storage.prefs.StorageProvider
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class StorageModule {

    @Singleton
    @Provides
    fun bindOnDiscStorage(
        storage: StorageProvider,
        converter: DataConverter,
        encoding: DataEncoding
    ): Storage = StorageManager(storage, converter, encoding)

    @Provides
    fun provideSharePrefStorageProvider(provider: SharedPrefsStorageProvider): StorageProvider =
        provider

}
