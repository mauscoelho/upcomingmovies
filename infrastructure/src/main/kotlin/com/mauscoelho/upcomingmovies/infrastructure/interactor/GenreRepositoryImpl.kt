package com.mauscoelho.upcomingmovies.infrastructure.interactor

import com.mauscoelho.upcomingmovies.infrastructure.boundary.GenreRepository
import com.mauscoelho.upcomingmovies.infrastructure.network.TmdbNetwork
import com.mauscoelho.upcomingmovies.model.Genre
import com.mauscoelho.upcomingmovies.model.Genres
import io.paperdb.Paper
import rx.Observable

class GenreRepositoryImpl(val tmdbNetwork: TmdbNetwork,
                          val genresCollection: String) : GenreRepository {

    override fun getGenres(genreIds: List<Int>, api_key: String, language: String): Observable<List<Genre>> {
        val genres = Paper.book().read<List<Genre>>(genresCollection, listOf())
        return Observable.just(genres.filter { genreIds.contains(it.id) })
    }

    override fun loadGenres(api_key: String, language: String): Observable<Genres> {
        return tmdbNetwork.getGenres(api_key, language)
                .map {
                    if (!Paper.book().exist(genresCollection)) {
                        Paper.book().write(genresCollection, it.genres)
                    }
                    it
                }
    }
}