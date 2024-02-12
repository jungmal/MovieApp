package jungmal.movieapp.library.network.di

import android.util.Log
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import jungmal.movieapp.library.network.api.ApiService
import jungmal.movieapp.library.network.retrofit.NetworkRequestFactory
import jungmal.movieapp.library.network.retrofit.NetworkRequestFactoryImpl
import jungmal.movieapp.library.network.retrofit.StringConverterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    @Singleton
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(
        interceptor: HttpLoggingInterceptor
    ): OkHttpClient {
        val builder = OkHttpClient.Builder()
        if (true) {
            builder.addInterceptor(interceptor)
        }
        return builder.build()
    }

    @Provides
    @Singleton
    fun provideGson(): Gson {
        return Gson()
    }

    @Provides
    @Singleton
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        gson: Gson
    ): Retrofit {
        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(logBaseUrl(baseUrl = "https://raw.githubusercontent.com/jungmal/api/main/"))
            .addConverterFactory(StringConverterFactory(gson))
            .build()
    }

    private fun logBaseUrl(baseUrl: String): String {
        Log.d("NetworkModule", baseUrl)
        return baseUrl
    }

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ApiService = retrofit.create(ApiService::class.java)

    @Provides
    @Singleton
    fun bindNetworkRequestFactory(networkRequestFactory: NetworkRequestFactoryImpl): NetworkRequestFactory =
        networkRequestFactory

}
