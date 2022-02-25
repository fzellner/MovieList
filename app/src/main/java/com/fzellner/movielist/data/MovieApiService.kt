package com.fzellner.movielist.data

import com.fzellner.movielist.data.entities.MovieResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApiService {

    @GET("movie/popular")
    suspend fun getPopularMovies(@Query("page") page: Int): MovieResponse
//    suspend fun getPopularMovies(@Query("page") page: Int): Response<MovieResponse>
}