package com.oguzdogdu.moviedbapp.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.oguzdogdu.moviedbapp.domain.usecase.NowPlayingUseCase
import com.oguzdogdu.moviedbapp.domain.usecase.UpComingUseCase
import com.oguzdogdu.moviedbapp.util.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val upComingUseCase: UpComingUseCase,
    private val nowPlayingUseCase: NowPlayingUseCase,
) : ViewModel() {
    private val _nowPlaying = MutableStateFlow(MainState())
    val nowPlaying: StateFlow<MainState>
        get() = _nowPlaying

    private val _upComing = MutableStateFlow(MainState())
    val upComing: StateFlow<MainState>
        get() = _upComing

    init {
        getUpComingMovies()
        getNowPlayingMovies()
    }

    private fun getNowPlayingMovies() {
        nowPlayingUseCase().onEach {
            when (it) {
                is Result.Loading -> {
                    _nowPlaying.value = MainState(isLoading = true)
                }
                is Result.Success -> {
                    _nowPlaying.value = MainState(data = it.data)
                }
                is Result.Error -> {
                    _nowPlaying.value = MainState(error = it.message ?: "")
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun getUpComingMovies() {
        upComingUseCase().onEach {
            when (it) {
                is Result.Loading -> {
                    _upComing.value = MainState(isLoading = true)
                }
                is Result.Success -> {
                    _upComing.value = MainState(data = it.data)
                }
                is Result.Error -> {
                    _upComing.value = MainState(error = it.message ?: "")
                }
            }
        }.launchIn(viewModelScope)
    }
}