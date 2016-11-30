package com.mauscoelho.upcomingmovies.domain.boundary

import com.mauscoelho.upcomingmovies.model.Genres
import rx.Observable


interface GenreService {
    fun loadGenres(api_key: String, language: String): Observable<Genres>
}