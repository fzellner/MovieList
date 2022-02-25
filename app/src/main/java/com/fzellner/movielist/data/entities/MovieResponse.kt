package com.fzellner.movielist.data.entities


data class MovieResponse(
    val page: Int,
    val results: List<MovieDTO>,
    val total_results: Int,
    val total_pages: Int
)

data class MovieDTO(
    val id: Long,
    val poster_path: String,
    val original_title: String,
    val genre_ids: List<Int>,
    val release_date: String,
    val overview: String
)