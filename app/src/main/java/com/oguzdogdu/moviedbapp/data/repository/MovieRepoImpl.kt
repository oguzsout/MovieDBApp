package com.oguzdogdu.moviedbapp.data.repository

import com.oguzdogdu.moviedbapp.data.service.MovieService
import com.oguzdogdu.moviedbapp.data.MoviesResponse
import com.oguzdogdu.moviedbapp.domain.repository.MovieRepoInterface
import com.oguzdogdu.moviedbapp.util.Constants.API_KEY
import javax.inject.Inject

class MovieRepoImpl @Inject constructor(private val service: MovieService) : MovieRepoInterface {
    override suspend fun getPopularMovies(page: Int): MoviesResponse {
        return service.getPopular(API_KEY, page)
    }

    override suspend fun getNowPlayingMovies(page: Int): MoviesResponse {
        return service.getNowPlaying(API_KEY, page)
    }

    override suspend fun getUpcomingMovies(page: Int): MoviesResponse {
        return service.getUpcoming(API_KEY, page)
    }
}