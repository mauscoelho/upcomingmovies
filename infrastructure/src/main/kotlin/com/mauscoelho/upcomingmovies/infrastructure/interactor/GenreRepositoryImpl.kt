package com.mauscoelho.upcomingmovies.infrastructure.interactor

import android.util.Log
import com.mauscoelho.upcomingmovies.infrastructure.boundary.GenreRepository
import com.mauscoelho.upcomingmovies.infrastructure.network.TmdbNetwork
import com.mauscoelho.upcomingmovies.model.Genre
import com.mauscoelho.upcomingmovies.model.Genres
import rx.Observable

class GenreRepositoryImpl(val tmdbNetwork: TmdbNetwork) : GenreRepository {

    val genresCache = mutableListOf<Genre>()

    override fun getGenres(genreIds: List<Int>, api_key: String, language: String): Observable<List<Genre>> {
        Log.i("teste", "cache ${genresCache.size}")
        return Observable.just(genresCache.filter { genreIds.contains(it.id) })
    }

    override fun loadGenres(api_key: String, language: String): Observable<Genres> {
        return tmdbNetwork.getGenres(api_key, language)
                .map {
                    if (genresCache.isEmpty()) {
                        genresCache.addAll(it.genres)
                        Log.i("teste", "add to cache ${genresCache.size}")
                    }
                    it
                }
    }
}