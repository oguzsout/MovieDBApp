package com.oguzdogdu.moviedbapp.ui.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.oguzdogdu.moviedbapp.databinding.MainItemRowBinding
import com.oguzdogdu.moviedbapp.domain.model.NetworkMovie

class MainMovieAdapter : RecyclerView.Adapter<MainMovieAdapter.MainViewHolder>() {
    inner class MainViewHolder(private val binding: MainItemRowBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: NetworkMovie) {
            binding.apply {
                imgMovie.load(movie.posterUrl)
                tvMovieTitle.text = movie.title
                tvDescription.text = movie.overview
                tvRealease.text = movie.realease
            }
            binding.root.setOnClickListener {
                onItemClickListener?.let {
                    it(movie)
                }
            }
        }
    }

    private var onItemClickListener: ((NetworkMovie) -> Unit)? = null

    fun setOnItemClickListener(listener: (NetworkMovie) -> Unit) {
        onItemClickListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        return MainViewHolder(MainItemRowBinding.inflate(LayoutInflater.from(parent.context),
            parent,
            false))
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val movieItem = movies[position]
        holder.bind(movieItem)
    }

    private val diffUtil = object : DiffUtil.ItemCallback<NetworkMovie>() {
        override fun areItemsTheSame(oldItem: NetworkMovie, newItem: NetworkMovie): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: NetworkMovie, newItem: NetworkMovie): Boolean {
            return oldItem == newItem
        }
    }
    private val recyclerListDiffer = AsyncListDiffer(this, diffUtil)

    var movies: List<NetworkMovie>
        get() = recyclerListDiffer.currentList
        set(value) = recyclerListDiffer.submitList(value)

    override fun getItemCount() = movies.size
}