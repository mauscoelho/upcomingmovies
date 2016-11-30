package com.mauscoelho.upcomingmovies.infraestruture.interactor

import com.mauscoelho.upcomingmovies.infraestruture.boundary.GenreRepository
import com.mauscoelho.upcomingmovies.infraestruture.network.TmdbNetwork
import com.mauscoelho.upcomingmovies.model.Genre
import rx.Observable

class GenreRepositoryImpl(val tmdbNetwork: TmdbNetwork) : GenreRepository {

    val genresCache = mutableListOf<Genre>()

    override fun getGenres(genreIds: List<Int>, api_key: String, language: String): Observable<List<Genre>> {
        if (genresCache.isNotEmpty()) {
            return Observable.just(genresCache.filter { genreIds.contains(it.id) })
        } else {
            return tmdbNetwork.getGenres(api_key, language)
                    .map {
                        if (genresCache.isEmpty())
                            genresCache.addAll(it.genres)

                        it.genres.filter { genreIds.contains(it.id) }
                    }
        }
    }
}