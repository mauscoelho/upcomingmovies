package com.mauscoelho.upcomingmovies.infraestruture.interactor

import com.mauscoelho.upcomingmovies.infraestruture.UpcomingMoviesRepository
import com.mauscoelho.upcomingmovies.infraestruture.network.TmdbNetwork
import com.mauscoelho.upcomingmovies.model.UpcomingMovies
import rx.Observable


class UpcomingMoviesRepositoryImpl(val tmdbNetwork: TmdbNetwork) : UpcomingMoviesRepository {
    override fun getUpcomingMovies(api_key: String, language: String, page: Int): Observable<UpcomingMovies> {
        return tmdbNetwork.getUpcomingMovies(api_key, language, page)
    }
}