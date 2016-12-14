package com.mauscoelho.upcomingmovies.infrastructure.interactor

import com.fasterxml.jackson.databind.ObjectMapper
import com.mauscoelho.upcomingmovies.infrastructure.boundary.GenreRepository
import com.mauscoelho.upcomingmovies.infrastructure.network.TmdbNetwork
import com.mauscoelho.upcomingmovies.model.Genre
import com.mauscoelho.upcomingmovies.model.Genres
import com.snappydb.DB
import rx.Observable

class GenreRepositoryImpl(private val tmdbNetwork: TmdbNetwork,
                          private val genresCollection: String,
                          private val snappyDb: DB,
                          private val mapper : ObjectMapper) : GenreRepository {

    override fun getGenres(genreIds: List<Int>): Observable<List<Genre>> {
        val json = snappyDb.get(genresCollection)
        val genres = mapper.readValue(json, Array<Genre>::class.java)
        return Observable.just(genres.filter { genreIds.contains(it.id) })
    }

    override fun loadGenres(apiKey: String, language: String): Observable<Genres> {
        return tmdbNetwork.getGenres(apiKey, language)
                .map {
                    if (!snappyDb.exists(genresCollection)) {
                        val json = mapper.writeValueAsString(it.genres)
                        snappyDb.put(genresCollection, json)
                    }
                    it
                }
    }
}