package com.mauscoelho.upcomingmovies.domain.interactor

import com.mauscoelho.upcomingmovies.domain.boundary.GenresService
import com.mauscoelho.upcomingmovies.domain.boundary.UpcomingMoviesService
import com.mauscoelho.upcomingmovies.infraestruture.UpcomingMoviesRepository
import com.mauscoelho.upcomingmovies.model.Movie
import com.mauscoelho.upcomingmovies.model.MovieResponse
import rx.Observable
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers


class UpcomingMoviesServiceImpl(
        val upcomingMoviesRepository: UpcomingMoviesRepository,
        val genresService: GenresService) : UpcomingMoviesService {

    override fun getUpcomingMovies(api_key: String, language: String, page: Int): Observable<Movie> {
        return upcomingMoviesRepository.getUpcomingMovies(api_key, language, page)
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
                        genresService.fetchGenres(api_key, language, it)
                    }
                }
    }

    override fun getMovie(movieId: Int, api_key: String, language: String): Observable<MovieResponse> {
        return upcomingMoviesRepository.getMovie(movieId, api_key, language)
    }
}