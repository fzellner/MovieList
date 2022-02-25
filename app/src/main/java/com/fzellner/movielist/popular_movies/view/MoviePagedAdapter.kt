package com.fzellner.movielist.popular_movies.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.ImageLoaderFactory
import coil.load
import com.fzellner.movielist.databinding.PopularMovieViewHolderBinding
import com.fzellner.movielist.domain.model.Movie

class MoviePagedAdapter(private val onMovieClicked: (movie: Movie) -> Unit) :
    PagingDataAdapter<Movie, MoviePagedAdapter.MovieViewHolder>(DiffCallback()) {

    class MovieViewHolder(private val itemBinding: PopularMovieViewHolderBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {


        fun bind(movie: Movie) {
            itemBinding.moviePosterImageView.load(movie.moviePoster) {
                crossfade(true)
            }
            itemBinding.movieTitleTextView.text = movie.title
        }

    }

    class DiffCallback : DiffUtil.ItemCallback<Movie>() {
        override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem == newItem
        }
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        getItem(position)?.let { movie ->
            holder.bind(movie)
            holder.itemView.setOnClickListener {
                onMovieClicked(movie)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val binding = PopularMovieViewHolderBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return MovieViewHolder(binding)
    }

}