package com.oguzdogdu.moviedbapp.data.model

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
    realease = realease,
    voteAverage = voteAverage,
    backdrop = backdrop
)