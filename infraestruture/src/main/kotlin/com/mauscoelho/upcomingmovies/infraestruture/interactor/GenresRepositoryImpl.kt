package com.mauscoelho.upcomingmovies.infraestruture.interactor

import com.mauscoelho.upcomingmovies.infraestruture.boundary.GenresRepository
import com.mauscoelho.upcomingmovies.infraestruture.network.TmdbNetwork
import com.mauscoelho.upcomingmovies.model.Genres
import rx.Observable


class GenresRepositoryImpl(val tmdbNetwork: TmdbNetwork) : GenresRepository {
    override fun getGenres(api_key: String, language: String): Observable<Genres> {
        return tmdbNetwork.getGenres(api_key,language)
    }
}