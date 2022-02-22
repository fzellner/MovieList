package com.fzellner.movielist.popular_movies.di

import com.fzellner.movielist.popular_movies.interactor.PopularMoviesUseCase
import com.fzellner.movielist.popular_movies.repository.PopularMoviesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object PopularMoviewsModule {

    @Singleton
    @Provides
    fun provideUseCase(repository: PopularMoviesRepository) = PopularMoviesUseCase(repository)

}