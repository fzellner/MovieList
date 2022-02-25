package com.fzellner.movielist.popular_movies.view

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.fzellner.movielist.data.entities.MovieResponse
import com.fzellner.movielist.domain.model.Movie
import com.fzellner.movielist.popular_movies.interactor.PopularMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

@HiltViewModel
class PopularMoviesViewModel @Inject constructor(
    private val useCase: PopularMoviesUseCase
) : ViewModel() {

    fun getMovies(): Flow<PagingData<Movie>> {
        return useCase().cachedIn(viewModelScope)
    }


}