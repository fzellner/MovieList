package com.fzellner.movielist.data.entities.dto


data class MovieResponse(
    val page: Int,
    val results: List<MovieDTO>,
    val total_results: Int,
    val total_pages: Int
)

data class MovieDTO(
    val poster_path: String,
    val original_title: String,
    val genres_ids: List<Int>,
    val releade_date: String,
    val overview: String
)