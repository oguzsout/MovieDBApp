package com.oguzdogdu.moviedbapp.ui.main

import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.lifecycle.coroutineScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.interfaces.ItemClickListener
import com.denzcoskun.imageslider.models.SlideModel
import com.google.android.material.snackbar.Snackbar
import com.oguzdogdu.moviedbapp.R
import com.oguzdogdu.moviedbapp.base.BaseFragment
import com.oguzdogdu.moviedbapp.databinding.FragmentMainMovieBinding
import com.oguzdogdu.moviedbapp.ui.main.adapter.MainMovieAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainMovieFragment :
    BaseFragment<FragmentMainMovieBinding>(FragmentMainMovieBinding::inflate) {

    private val viewModel: MainViewModel by viewModels()
    private val movieAdapter: MainMovieAdapter by lazy { MainMovieAdapter() }
    private val imageList = ArrayList<SlideModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        observeNowPlayingData()
        observeUpComingData()
        navigateScreen()
        whenClickedSlider()
        pullToRefresh()
    }

    private fun setupRecyclerView() {
        binding.rvMain.apply {
            adapter = movieAdapter
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
        }
    }

    private fun observeNowPlayingData() {
        lifecycle.coroutineScope.launchWhenCreated {
            viewModel.nowPlaying.collect { nowplaying ->
                when {
                    nowplaying.isLoading -> {

                    }
                    nowplaying.error.isNotEmpty() -> {
                        Snackbar.make(requireView(), "Check Connectivity", Snackbar.LENGTH_LONG).show()
                    }
                    nowplaying.data?.isNotEmpty() == true -> {
                        listForImageSlider(nowplaying)
                    }
                }
            }
        }
    }

    private fun observeUpComingData() {
        lifecycle.coroutineScope.launchWhenCreated {
            viewModel.upComing.collect { upcoming ->
                if (upcoming.isLoading) {
                    showProgressBar()
                }
                if (upcoming.error.isNotBlank()) {
                    Snackbar.make(requireView(), "Check Connectivity", Snackbar.LENGTH_LONG).show()
                }

                upcoming.data?.let {
                    hideProgressBar()
                    movieAdapter.movies = it
                }
            }
        }
    }

    private fun navigateScreen() {
        movieAdapter.setOnItemClickListener {
            val action =
                MainMovieFragmentDirections.actionMainMovieFragmentToDetailMovieFragment(
                    it)
            findNavController().navigate(action)
        }
    }

    private fun whenClickedSlider() {
        binding.imageSlider.setItemClickListener(object : ItemClickListener {
            override fun onItemSelected(position: Int) {
                movieAdapter.setOnItemClickListener {
                    val action =
                        MainMovieFragmentDirections.actionMainMovieFragmentToDetailMovieFragment(it)
                    findNavController().navigate(action)
                }
            }
        })
    }

    private fun pullToRefresh() {
        binding.swipe.setProgressBackgroundColorSchemeColor(
            ContextCompat.getColor(
                requireContext(),
                R.color.white
            )
        )
        binding.swipe.setColorSchemeColors(Color.RED)

        binding.swipe.setOnRefreshListener {
            observeUpComingData()
            binding.swipe.isRefreshing = false
        }
    }

    private fun listForImageSlider(nowPlaying: MainState) {

        imageList.add(SlideModel(nowPlaying.data?.get(0)?.slider,
            nowPlaying.data?.get(0)?.title, ScaleTypes.CENTER_CROP))
        imageList.add(SlideModel(nowPlaying.data?.get(1)?.slider,
            nowPlaying.data?.get(1)?.title, ScaleTypes.CENTER_CROP))
        imageList.add(SlideModel(nowPlaying.data?.get(2)?.slider,
            nowPlaying.data?.get(2)?.title, ScaleTypes.CENTER_CROP))
        imageList.add(SlideModel(nowPlaying.data?.get(3)?.slider,
            nowPlaying.data?.get(3)?.title, ScaleTypes.CENTER_CROP))
        binding.imageSlider.setImageList(imageList)
    }

    private fun hideProgressBar() {
        binding.progressBar.visibility = View.INVISIBLE
    }

    private fun showProgressBar() {
        binding.progressBar.visibility = View.VISIBLE
    }
}
