package com.fzellner.movielist.data.di

import com.fzellner.movielist.data.RetrofitBuilder
import com.fzellner.movielist.data.MovieApiService
import com.fzellner.movielist.data.repository.PopularMoviesRepositoryImpl
import com.fzellner.movielist.popular_movies.repository.PopularMoviesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun providesAPI(): MovieApiService {
        return RetrofitBuilder.builder(MovieApiService::class.java)
    }

    @Singleton
    @Provides
    fun providesRepository(apiService: MovieApiService): PopularMoviesRepository = PopularMoviesRepositoryImpl(apiService)


}