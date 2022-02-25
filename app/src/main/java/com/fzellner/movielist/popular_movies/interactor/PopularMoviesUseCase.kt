package com.fzellner.movielist.popular_movies.interactor

import androidx.paging.PagingData
import com.fzellner.movielist.data.entities.MovieResponse
import com.fzellner.movielist.domain.model.Movie
import com.fzellner.movielist.popular_movies.repository.PopularMoviesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PopularMoviesUseCase @Inject constructor(private val repository: PopularMoviesRepository) {

    operator fun invoke(): Flow<PagingData<Movie>> {
        return repository.getPopularMovies()
    }

}
