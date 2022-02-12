package com.oguzdogdu.moviedbapp.util

object Constants {
    const val TMDB_BASE_URL = "https://api.themoviedb.org/3/"
    const val API_KEY = ""
    const val PAGE = 1

    //https://www.themoviedb.org/talk/5aeaaf56c3a3682ddf0010de?language=tr-TR
    private const val IMAGE_BASE_URL = "https://image.tmdb.org/t/p/"
    private const val IMAGE_SIZE_W185 = "w185"
    private const val IMAGE_SIZE_W780 = "w780"
    private const val IMAGE_SIZE_w1280 = "w1280"

    const val POSTER_URL_SLIDER = IMAGE_BASE_URL + IMAGE_SIZE_w1280
    const val POSTER_URL = IMAGE_BASE_URL + IMAGE_SIZE_W185
    const val BACKDROP_URL = IMAGE_BASE_URL + IMAGE_SIZE_W780
}