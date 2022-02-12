package com.oguzdogdu.moviedbapp.data.service

import com.oguzdogdu.moviedbapp.data.MoviesResponse
import com.oguzdogdu.moviedbapp.util.Constants.API_KEY
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieService {
    @GET("movie/popular")
    suspend fun getPopular(
        @Query("api_key") api_key: String = API_KEY,
        @Query("page") page: Int
    ): MoviesResponse

    @GET("movie/now_playing")
    suspend fun getNowPlaying(
        @Query("api_key") api_key: String = API_KEY,
        @Query("page") page: Int
    ): MoviesResponse

    @GET("movie/upcoming")
    suspend fun getUpcoming(
        @Query("api_key") api_key: String = API_KEY,
        @Query("page") page: Int
    ): MoviesResponse
}