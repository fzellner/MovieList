package com.fzellner.movielist.popular_movies.repository

import androidx.paging.PagingData
import com.fzellner.movielist.data.entities.MovieResponse
import com.fzellner.movielist.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface PopularMoviesRepository {
    fun getPopularMovies(): Flow<PagingData<Movie>>
}