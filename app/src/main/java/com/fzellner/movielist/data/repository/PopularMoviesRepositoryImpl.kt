package com.fzellner.movielist.data.repository

import com.fzellner.movielist.data.MovieApiService
import com.fzellner.movielist.domain.utils.call
import com.fzellner.movielist.popular_movies.repository.PopularMoviesRepository
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow

class PopularMoviesRepositoryImpl(private val service: MovieApiService) : PopularMoviesRepository {

    override fun getPopularMovies() = flow {
        service.getPopularMovies().call().collect {
            emit(it)
        }
    }

}