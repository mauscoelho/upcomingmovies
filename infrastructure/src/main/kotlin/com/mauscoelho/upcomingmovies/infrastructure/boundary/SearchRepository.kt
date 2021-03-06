package com.mauscoelho.upcomingmovies.infrastructure.boundary

import com.mauscoelho.upcomingmovies.model.UpcomingMovies
import rx.Observable


interface SearchRepository {
    fun search(query: String, apiKey: String, language: String, page: Int): Observable<UpcomingMovies>
}