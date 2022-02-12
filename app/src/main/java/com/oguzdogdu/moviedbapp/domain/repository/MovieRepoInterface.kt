package com.oguzdogdu.moviedbapp.domain.repository

import com.oguzdogdu.moviedbapp.data.model.MoviesResponse

interface MovieRepoInterface {
    suspend fun getNowPlayingMovies(page: Int): MoviesResponse
    suspend fun getUpcomingMovies(page: Int): MoviesResponse
}