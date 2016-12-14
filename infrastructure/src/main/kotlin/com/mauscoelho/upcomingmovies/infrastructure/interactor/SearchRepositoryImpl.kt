package com.mauscoelho.upcomingmovies.infrastructure.interactor

import com.mauscoelho.upcomingmovies.infrastructure.boundary.SearchRepository
import com.mauscoelho.upcomingmovies.infrastructure.network.TmdbNetwork
import com.mauscoelho.upcomingmovies.model.UpcomingMovies
import rx.Observable


class SearchRepositoryImpl(private val tmdbNetwork: TmdbNetwork) : SearchRepository {
    override fun search(query: String, apiKey: String, language: String, page: Int): Observable<UpcomingMovies> {
        return tmdbNetwork.search(query, apiKey, language, page)
    }
}