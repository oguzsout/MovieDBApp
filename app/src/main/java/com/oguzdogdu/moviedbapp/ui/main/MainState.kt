package com.oguzdogdu.moviedbapp.ui.main


import com.oguzdogdu.moviedbapp.domain.model.NetworkMovie

data class MainState(
    val isLoading : Boolean = false,
    val data: List<NetworkMovie>? = null,
    val error : String = ""
)
