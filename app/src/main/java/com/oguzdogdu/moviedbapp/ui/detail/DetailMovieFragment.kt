package com.oguzdogdu.moviedbapp.ui.detail

import android.os.Bundle
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import coil.load
import com.oguzdogdu.moviedbapp.base.BaseFragment
import com.oguzdogdu.moviedbapp.databinding.FragmentDetailMovieBinding

class DetailMovieFragment : BaseFragment<FragmentDetailMovieBinding>(FragmentDetailMovieBinding::inflate) {
    private val args : DetailMovieFragmentArgs by navArgs()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setData()
        backStack()
    }

    private fun setData() {
        val movieList = args.movieId
        binding.imageListItem.load(movieList?.backDrops)
        binding.tvRealeaseDetail.text = movieList?.realease
        binding.tvVote.text = movieList?.voteAverage.toString()
        binding.tvTitle.text = movieList?.title
        binding.tvSummary.text = movieList?.overview
    }

    private fun backStack() {
        val callBack = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                findNavController().popBackStack()
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(callBack)
    }
}