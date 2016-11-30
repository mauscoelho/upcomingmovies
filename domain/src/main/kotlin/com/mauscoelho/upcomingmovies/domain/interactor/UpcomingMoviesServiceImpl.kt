package com.mauscoelho.upcomingmovies.domain.interactor

import com.mauscoelho.upcomingmovies.domain.boundary.UpcomingMoviesService
import com.mauscoelho.upcomingmovies.infrastructure.boundary.GenreRepository
import com.mauscoelho.upcomingmovies.infrastructure.boundary.SearchRepository
import com.mauscoelho.upcomingmovies.infrastructure.boundary.UpcomingMoviesRepository
import com.mauscoelho.upcomingmovies.model.Movie
import rx.Observable
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers


class UpcomingMoviesServiceImpl(
        val upcomingMoviesRepository: UpcomingMoviesRepository,
        val genreRepository: GenreRepository,
        val searchRepository: SearchRepository) : UpcomingMoviesService {

    override fun getUpcomingMovies(apiKey: String, language: String, page: Int): Observable<Movie> {
        return upcomingMoviesRepository.getUpcomingMovies(apiKey, language, page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map { item ->
                    item.results.map {
                        it.currentPage = item.page
                        it.totalPages = item.total_pages
                        it
                    }
                }
                .flatMap { items ->
                    Observable.from(items).flatMap {
                        fetchGenre(it, apiKey, language)
                    }
                }
    }

    private fun fetchGenre(movie: Movie, api_key: String, language: String): Observable<Movie> {
        return genreRepository.getGenres(movie.genre_ids, api_key, language)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
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
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map { item ->
                    item.results.map {
                        it.currentPage = item.page
                        it.totalPages = item.total_pages
                        it
                    }
                }
                .flatMap { items ->
                    Observable.from(items).flatMap {
                        fetchGenre(it, apiKey, language)
                    }
                }
    }
}