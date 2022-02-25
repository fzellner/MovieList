package com.fzellner.movielist.popular_movies.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.fzellner.movielist.databinding.FragmentPopularMoviesBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class PopularMoviesFragment : Fragment() {

    private val viewModel by viewModels<PopularMoviesViewModel>()
    private lateinit var _binding: FragmentPopularMoviesBinding
    private lateinit var moviePagedAdapter: MoviePagedAdapter
    private val binding: FragmentPopularMoviesBinding
        get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPopularMoviesBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupAdapter()
        viewLifecycleOwner.lifecycleScope.launchWhenCreated {
            viewModel.getMovies().collectLatest {
                moviePagedAdapter.submitData(it)
            }
        }
    }

    private fun setupAdapter() {

        moviePagedAdapter = MoviePagedAdapter {
            Toast.makeText(context, it.title, Toast.LENGTH_SHORT).show()
        }
        binding.movieListRecyclerView.apply {
            layoutManager = GridLayoutManager(context, 2)
            adapter = moviePagedAdapter
        }
    }

}