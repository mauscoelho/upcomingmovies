package com.mauscoelho.upcomingmovies.infrastructure.interactor

import com.mauscoelho.upcomingmovies.infrastructure.boundary.UpcomingMoviesRepository
import com.mauscoelho.upcomingmovies.infrastructure.network.TmdbNetwork
import com.mauscoelho.upcomingmovies.model.Movie
import com.mauscoelho.upcomingmovies.model.UpcomingMovies
import rx.Observable


class UpcomingMoviesRepositoryImpl(private val tmdbNetwork: TmdbNetwork) : UpcomingMoviesRepository {
    override fun getMovie(movieId: Int, apiKey: String, language: String): Observable<Movie> {
        return tmdbNetwork.getMovie(movieId, apiKey,language)
    }

    override fun getUpcomingMovies(apiKey: String, language: String, page: Int): Observable<UpcomingMovies> {
        return tmdbNetwork.getUpcomingMovies(apiKey, language, page)
    }
}