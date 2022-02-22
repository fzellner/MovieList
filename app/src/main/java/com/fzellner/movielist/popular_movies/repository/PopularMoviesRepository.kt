package com.fzellner.movielist.popular_movies.repository

import com.fzellner.movielist.data.entities.dto.MovieResponse
import kotlinx.coroutines.flow.Flow

interface PopularMoviesRepository {
    fun getPopularMovies(): Flow<MovieResponse>
}