package com.mauscoelho.upcomingmovies.infrastructure.interactor

import com.mauscoelho.upcomingmovies.infrastructure.boundary.UpcomingMoviesRepository
import com.mauscoelho.upcomingmovies.infrastructure.network.TmdbNetwork
import com.mauscoelho.upcomingmovies.model.Movie
import com.mauscoelho.upcomingmovies.model.UpcomingMovies
import rx.Observable


class UpcomingMoviesRepositoryImpl(val tmdbNetwork: TmdbNetwork) : UpcomingMoviesRepository {
    override fun getMovie(movieId: Int, api_key: String, language: String): Observable<Movie> {
        return tmdbNetwork.getMovie(movieId, api_key,language)
    }

    override fun getUpcomingMovies(api_key: String, language: String, page: Int): Observable<UpcomingMovies> {
        return tmdbNetwork.getUpcomingMovies(api_key, language, page)
    }
}