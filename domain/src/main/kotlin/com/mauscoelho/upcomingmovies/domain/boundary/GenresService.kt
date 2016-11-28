package com.mauscoelho.upcomingmovies.domain.boundary

import com.mauscoelho.upcomingmovies.model.Genre
import rx.Observable


interface GenresService {
    fun getGenres(api_key: String, language: String) : Observable<Genre>
}