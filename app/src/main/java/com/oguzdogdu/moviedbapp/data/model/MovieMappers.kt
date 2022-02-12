package com.oguzdogdu.moviedbapp.data

import com.oguzdogdu.moviedbapp.domain.model.Movies
import com.oguzdogdu.moviedbapp.domain.model.NetworkMovie

fun MoviesResponse.asMovies() = Movies(results.map {
    it.asMovie()
}, totalResults, totalPages)

fun Movie.asMovie() = NetworkMovie(
    id = id,
    title = title,
    overview = overview,
    posterPath = posterPath,
    voteAverage = voteAverage,
)