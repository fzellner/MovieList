package com.fzellner.movielist.popular_movies.interactor

import com.fzellner.movielist.data.entities.dto.MovieResponse
import com.fzellner.movielist.popular_movies.repository.PopularMoviesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PopularMoviesUseCase @Inject constructor(private val repository: PopularMoviesRepository) {

    operator fun invoke(): Flow<MovieResponse> {
        return repository.getPopularMovies()
    }

}
