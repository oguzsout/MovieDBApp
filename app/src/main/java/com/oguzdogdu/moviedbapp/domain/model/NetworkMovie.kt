package com.oguzdogdu.moviedbapp.domain.model

import android.os.Parcelable
import com.oguzdogdu.moviedbapp.util.Constants
import kotlinx.parcelize.Parcelize

@Parcelize
data class NetworkMovie(
    val id: Int,
    val title: String,
    val overview: String,
    val posterPath: String,
    val voteAverage: Double?,
) : Parcelable {
    val posterUrl = Constants.POSTER_URL + posterPath
}