package com.fzellner.movielist.domain.model

data class Movie(
    val id:Long,
    val title:String,
    val moviePoster:String,
    val releaseDate:String,
    val overView:String,
    val genre: List<Int>
)