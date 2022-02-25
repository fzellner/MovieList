package com.fzellner.movielist.data.repository

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.fzellner.movielist.data.MovieApiService
import com.fzellner.movielist.data.entities.MovieDTO
import com.fzellner.movielist.domain.model.Movie
import com.fzellner.movielist.domain.utils.call
import kotlinx.coroutines.flow.collect
import retrofit2.HttpException
import java.io.IOException
import java.lang.Exception
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*
import javax.inject.Inject

class MovieDataSource @Inject constructor(private val service: MovieApiService) :
    PagingSource<Int, Movie>() {

    companion object {
        private val INITIAL_LOAD = 20
        private val FIRST_PAGE = 1
        private val IMAGE_PATH = "https://image.tmdb.org/t/p/w342"
        private val DTO_DATE_FORMAT = "yyyy-MM-dd"
        private val VO_DATE_FORMAT = "dd/MM/yyyy"
        private val LOCALE_PT_BR = "pt_BR"
    }

    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {

        return state.anchorPosition?.let { position ->
            state.closestPageToPosition(position)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(position)?.nextKey?.minus(1)
        }

    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {

        val currentPage = params.key ?: FIRST_PAGE

        return try {
            val response = service.getPopularMovies(currentPage)
            val movies = mapMovies(response.results)
            val nextPage =
                if (movies.isNullOrEmpty()) null else getNextPage(currentPage, params.loadSize)

            LoadResult.Page(
                data = movies,
                prevKey = if (currentPage == FIRST_PAGE) null else currentPage,
                nextKey = nextPage
            )

        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (httpException: HttpException) {
            return LoadResult.Error(httpException)
        }

    }

    private fun mapMovies(results: List<MovieDTO>): List<Movie> {
        return results.map {
            Movie(
                id = it.id,
                title = it.original_title,
                moviePoster = mapMoviePoster(it.poster_path),
                releaseDate = formatReleaseDate(it.release_date),
                overView = it.overview,
                genre = it.genre_ids
            )
        }
    }

    private fun formatReleaseDate(releaseDate: String): String {
        val localDateTime = SimpleDateFormat(DTO_DATE_FORMAT, Locale.US)
        return SimpleDateFormat(VO_DATE_FORMAT, Locale(LOCALE_PT_BR)).format(
            localDateTime.parse(
                releaseDate,
            ) ?: throw Exception("Failed to parse release date!")
        )

    }

    private fun mapMoviePoster(posterPath: String) = IMAGE_PATH + posterPath

    private fun getNextPage(currentPage: Int, loadSize: Int) =
        currentPage + (loadSize / INITIAL_LOAD)


}