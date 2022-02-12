package com.oguzdogdu.moviedbapp.domain.repository

import com.oguzdogdu.moviedbapp.data.MoviesResponse

interface MovieRepoInterface {
    suspend fun getPopularMovies(page: Int): MoviesResponse
    suspend fun getNowPlayingMovies(page: Int): MoviesResponse
    suspend fun getUpcomingMovies(page: Int): MoviesResponse
}