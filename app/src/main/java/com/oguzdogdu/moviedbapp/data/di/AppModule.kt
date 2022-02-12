package com.oguzdogdu.moviedbapp.data.di

import com.oguzdogdu.moviedbapp.data.repository.MovieRepoImpl
import com.oguzdogdu.moviedbapp.data.service.MovieService
import com.oguzdogdu.moviedbapp.domain.repository.MovieRepoInterface
import com.oguzdogdu.moviedbapp.util.Constants.TMDB_BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {


    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(TMDB_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideMovieService(retrofit: Retrofit): MovieService {
        return retrofit.create(MovieService::class.java)
    }

    @Provides
    @Singleton
    fun provideRepository(service: MovieService): MovieRepoInterface {
        return MovieRepoImpl(service)
    }
}