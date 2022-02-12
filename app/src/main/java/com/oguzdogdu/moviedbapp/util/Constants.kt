package com.oguzdogdu.moviedbapp.util

object Constants {
    const val TMDB_BASE_URL = "https://api.themoviedb.org/3/"
    const val API_KEY = "59d4f8935222128a80fc9adf34b38f46"

    const val TAG = "Application Debug : "
    const val NOW_PLAYING_PAGE = 5
    const val UP_COMING_PAGE = 20

    //https://www.themoviedb.org/talk/5aeaaf56c3a3682ddf0010de?language=tr-TR
    private const val IMAGE_BASE_URL = "https://image.tmdb.org/t/p/"
    private const val IMAGE_SIZE_W185 = "w185"
    private const val IMAGE_SIZE_W780 = "w780"

    const val CAST_AVATAR_URL = IMAGE_BASE_URL + IMAGE_SIZE_W185
    const val CAST_IMDB_URL = "https://www.imdb.com/name/"
    const val POSTER_URL = IMAGE_BASE_URL + IMAGE_SIZE_W185
    const val BACKDROP_URL = IMAGE_BASE_URL + IMAGE_SIZE_W780
}