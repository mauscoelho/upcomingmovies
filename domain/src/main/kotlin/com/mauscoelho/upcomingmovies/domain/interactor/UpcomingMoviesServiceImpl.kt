package com.mauscoelho.upcomingmovies.domain.interactor

import com.mauscoelho.upcomingmovies.domain.boundary.UpcomingMoviesService
import com.mauscoelho.upcomingmovies.infrastructure.boundary.GenreRepository
import com.mauscoelho.upcomingmovies.infrastructure.boundary.SearchRepository
import com.mauscoelho.upcomingmovies.infrastructure.boundary.UpcomingMoviesRepository
import com.mauscoelho.upcomingmovies.model.Genre
import com.mauscoelho.upcomingmovies.model.Movie
import com.mauscoelho.upcomingmovies.model.UpcomingMovies
import rx.Observable
import rx.Scheduler


class UpcomingMoviesServiceImpl(
        private val upcomingMoviesRepository: UpcomingMoviesRepository,
        private val genreRepository: GenreRepository,
        private val searchRepository: SearchRepository,
        private val io: Scheduler,
        private val main: Scheduler) : UpcomingMoviesService {

    override fun getUpcomingMovies(apiKey: String, language: String, page: Int): Observable<Movie> {
        return upcomingMoviesRepository.getUpcomingMovies(apiKey, language, page)
                .subscribeOn(io)
                .observeOn(main)
                .map { toMovie(it) }
                .flatMap { toObservableMovie(it) }
    }

    override fun getMovie(movieId: Int, apiKey: String, language: String): Observable<Movie> {
        return upcomingMoviesRepository.getMovie(movieId, apiKey, language)
    }

    override fun searchMovies(query: String, apiKey: String, language: String, page: Int): Observable<Movie> {
        return searchRepository.search(query, apiKey, language, page)
                .subscribeOn(io)
                .observeOn(main)
                .map { toMovie(it) }
                .flatMap { toObservableMovie(it) }
    }

    private fun toObservableMovie(items: List<Movie>?): Observable<Movie>? = Observable.from(items).flatMap { fetchGenre(it) }

    private fun fetchGenre(movie: Movie): Observable<Movie> {
        return genreRepository.getGenres(movie.genre_ids)
                .subscribeOn(io)
                .observeOn(main)
                .flatMap { toObservableMovie(it, movie) }
    }

    private fun toObservableMovie(it: List<Genre>, movie: Movie): Observable<Movie>? {
        movie.genres = it
        return Observable.just(movie)
    }

    private fun toMovie(item: UpcomingMovies): List<Movie> =
            item.results.map {
                it.currentPage = item.page
                it.totalPages = item.total_pages
                it
            }
}