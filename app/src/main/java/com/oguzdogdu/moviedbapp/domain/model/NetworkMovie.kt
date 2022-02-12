package com.oguzdogdu.moviedbapp.domain.model

import android.os.Parcelable
import com.oguzdogdu.moviedbapp.util.Constants
import kotlinx.parcelize.IgnoredOnParcel
import kotlinx.parcelize.Parcelize

@Parcelize
data class NetworkMovie(
    val id: Int,
    val title: String,
    val overview: String,
    val posterPath: String,
    val realease : String,
    val voteAverage: Double?,
    val backdrop : String
) : Parcelable {
    @IgnoredOnParcel
    val posterUrl = Constants.POSTER_URL + posterPath
    @IgnoredOnParcel
    val backDrops = Constants.BACKDROP_URL + posterUrl
    @IgnoredOnParcel
    val slider = Constants.POSTER_URL_SLIDER + backdrop
}