package com.fzellner.movielist.popular_movies.view

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fzellner.movielist.data.entities.dto.MovieResponse
import com.fzellner.movielist.popular_movies.interactor.PopularMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

@HiltViewModel
class PopularMoviesViewModel @Inject constructor(
    private val useCase: PopularMoviesUseCase
) : ViewModel() {
    private val _moviesMutableLiveData = MutableLiveData<MovieResponse>()
    val moviesLiveData: LiveData<MovieResponse>
        get() = _moviesMutableLiveData


    init {
        useCase()
            .onEach {
                _moviesMutableLiveData.value = it
            }
            .onStart {
                Log.d("viewModel", "hmmm comecou a puxar..")
            }.launchIn(viewModelScope)
    }


}