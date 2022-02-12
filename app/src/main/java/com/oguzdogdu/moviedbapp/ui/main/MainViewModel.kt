package com.oguzdogdu.moviedbapp.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.oguzdogdu.moviedbapp.domain.repository.MovieRepoInterface
import com.oguzdogdu.moviedbapp.domain.usecase.UpComingUseCase
import com.oguzdogdu.moviedbapp.util.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val upComingUseCase: UpComingUseCase,
    private val repoInterface: MovieRepoInterface
) : ViewModel() {
//    private val _nowPlaying = MutableLiveData<Result<Movies>>()
//    val nowPlaying: LiveData<Result<Movies>>
//        get() = _nowPlaying

    private val _upComing = MutableStateFlow(MainState())
    val upComing: StateFlow<MainState>
        get() = _upComing

    init {
        getUpComingMovies()
    }

    //    private fun getNowPlayingMovies() {
//        nowPlayingUseCase().onEach {
//            when (it) {
//                is Result.Loading -> {
//                    _nowPlaying.value = MainState(isLoading = true)
//                }
//                is Result.Success -> {
//                    _nowPlaying.value = MainState(data = it.data)
//                }
//                is Result.Error -> {
//                    _nowPlaying.value = MainState(error = it.message ?: "")
//                }
//            }
//        }.launchIn(viewModelScope)
//    }
//
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