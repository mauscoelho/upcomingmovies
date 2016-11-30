package com.mauscoelho.upcomingmovies.infraestruture.boundary

import com.mauscoelho.upcomingmovies.model.Genre
import rx.Observable

interface GenreRepository {
    fun getGenres(genreIds: List<Int>, api_key: String, language: String): Observable<List<Genre>>
}