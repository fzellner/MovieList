package com.fzellner.movielist.data

import com.fzellner.movielist.data.entities.dto.MovieResponse
import retrofit2.Response
import retrofit2.http.GET

interface MovieApiService {

    @GET("movie/popular")
    suspend fun getPopularMovies(): Response<MovieResponse>
}