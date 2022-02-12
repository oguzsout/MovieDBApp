package com.oguzdogdu.moviedbapp.ui.main

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.coroutineScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.oguzdogdu.moviedbapp.base.BaseFragment
import com.oguzdogdu.moviedbapp.databinding.FragmentMainMovieBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainMovieFragment :
    BaseFragment<FragmentMainMovieBinding>(FragmentMainMovieBinding::inflate) {

    private val viewModel: MainViewModel by viewModels()
    private val movieAdapter: MainMovieAdapter by lazy { MainMovieAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        observeUpComingData()
    }

    private fun setupRecyclerView() {
        binding.rvMain.apply {
            adapter = movieAdapter
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
        }
    }

//    private fun observeNowPlayingData() {
//        lifecycle.coroutineScope.launchWhenCreated {
//            viewModel.nowPlaying.collect { nowplaying ->
//                when {
//                    nowplaying.isLoading -> {
//                        println("hi")
//                    }
//                    nowplaying.error.isNotEmpty() -> {
//                        Toast.makeText(requireContext(), nowplaying.error, Toast.LENGTH_SHORT)
//                            .show()
//                    }
//                    nowplaying.data?.isNotEmpty() == true -> {
//                        println(nowplaying.data)
//                    }
//                }
//            }
//        }
//    }

    private fun observeUpComingData() {
        lifecycle.coroutineScope.launchWhenCreated {
            viewModel.upComing.collect { upcoming ->
                if (upcoming.isLoading) {
                    Toast.makeText(requireContext(), "Loading", Toast.LENGTH_SHORT).show()
                }
                if (upcoming.error.isNotBlank()) {

                    Toast.makeText(requireContext(), upcoming.error, Toast.LENGTH_SHORT).show()
                }

                upcoming.data?.let {
                    Toast.makeText(requireContext(), "Success", Toast.LENGTH_SHORT).show()
                    println(it)
                    movieAdapter.movies = it
                }
            }
        }
    }
//        lifecycle.coroutineScope.launchWhenCreated {
//            viewModel.upComing.collect { upcoming ->
//                when {
//                    upcoming.isLoading -> {
//                        println("hi")
//                    }
//                    upcoming.error.isNotEmpty() -> {
//                        Toast.makeText(requireContext(), upcoming.error, Toast.LENGTH_SHORT)
//                            .show()
//                    }
//                    upcoming.data?.isNotEmpty() == true -> {
//                        println(upcoming.data)
//                        movieAdapter.movies = upcoming.data
//                    }
//                }
//            }
//        }
}
