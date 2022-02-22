package com.fzellner.movielist

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MovieListApplication: Application() {

    override fun onCreate() {
        super.onCreate()
    }
}

