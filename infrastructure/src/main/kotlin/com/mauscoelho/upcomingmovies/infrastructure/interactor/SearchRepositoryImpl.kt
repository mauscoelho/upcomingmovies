package com.mauscoelho.upcomingmovies.infrastructure.interactor

import com.mauscoelho.upcomingmovies.infrastructure.boundary.SearchRepository
import com.mauscoelho.upcomingmovies.infrastructure.network.TmdbNetwork
import com.mauscoelho.upcomingmovies.model.UpcomingMovies
import rx.Observable


class SearchRepositoryImpl(val tmdbNetwork: TmdbNetwork) : SearchRepository {
    override fun search(query: String, api_key: String, language: String, page: Int): Observable<UpcomingMovies> {
        return tmdbNetwork.search(query, api_key, language, page)
    }
}