package com.mauscoelho.upcomingmovies.infrastructure.boundary

import com.mauscoelho.upcomingmovies.model.Genre
import com.mauscoelho.upcomingmovies.model.Genres
import rx.Observable

interface GenreRepository {
    fun loadGenres(apiKey: String, language: String): Observable<Genres>
    fun getGenres(genreIds: List<Int>): Observable<List<Genre>>
}