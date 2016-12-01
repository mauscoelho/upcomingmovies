package com.mauscoelho.upcomingmovies.domain.interactor

import com.mauscoelho.upcomingmovies.domain.boundary.UpcomingMoviesService
import com.mauscoelho.upcomingmovies.infrastructure.boundary.GenreRepository
import com.mauscoelho.upcomingmovies.infrastructure.boundary.SearchRepository
import com.mauscoelho.upcomingmovies.infrastructure.boundary.UpcomingMoviesRepository
import com.mauscoelho.upcomingmovies.model.Movie
import com.mauscoelho.upcomingmovies.model.UpcomingMovies
import rx.Observable
import rx.Scheduler
import kotlin.reflect.jvm.internal.impl.javax.inject.Inject


class UpcomingMoviesServiceImpl @Inject constructor(
        val upcomingMoviesRepository: UpcomingMoviesRepository,
        val genreRepository: GenreRepository,
        val searchRepository: SearchRepository,
        val io: Scheduler,
        val main: Scheduler) : UpcomingMoviesService {

    override fun getUpcomingMovies(apiKey: String, language: String, page: Int): Observable<Movie> {
        return upcomingMoviesRepository.getUpcomingMovies(apiKey, language, page)
                .subscribeOn(io)
                .observeOn(main)
                .map { toMovie(it) }
                .flatMap { items ->
                    Observable.from(items).flatMap {
                        fetchGenre(it, apiKey, language)
                    }
                }
    }

    private fun fetchGenre(movie: Movie, api_key: String, language: String): Observable<Movie> {
        return genreRepository.getGenres(movie.genre_ids, api_key, language)
                .subscribeOn(io)
                .observeOn(main)
                .flatMap {
                    movie.genres = it
                    Observable.just(movie)
                }
    }


    override fun getMovie(movieId: Int, api_key: String, language: String): Observable<Movie> {
        return upcomingMoviesRepository.getMovie(movieId, api_key, language)
    }

    override fun searchMovies(query: String, apiKey: String, language: String, page: Int): Observable<Movie> {
        return searchRepository.search(query, apiKey, language, page)
                .subscribeOn(io)
                .observeOn(main)
                .map { toMovie(it) }
                .flatMap { items ->
                    Observable.from(items).flatMap {
                        fetchGenre(it, apiKey, language)
                    }
                }
    }

    private fun toMovie(item: UpcomingMovies): List<Movie> {
        return item.results.map {
            it.currentPage = item.page
            it.totalPages = item.total_pages
            it
        }
    }
}