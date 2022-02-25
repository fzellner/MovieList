package com.fzellner.movielist.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.fzellner.movielist.data.MovieApiService
import com.fzellner.movielist.domain.model.Movie
import com.fzellner.movielist.popular_movies.repository.PopularMoviesRepository
import kotlinx.coroutines.flow.Flow

class PopularMoviesRepositoryImpl(private val service: MovieApiService) : PopularMoviesRepository {

    override fun getPopularMovies(): Flow<PagingData<Movie>> {
       return Pager(
            config = PagingConfig(
                pageSize = 20,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { MovieDataSource(service) }
        ).flow
    }

}