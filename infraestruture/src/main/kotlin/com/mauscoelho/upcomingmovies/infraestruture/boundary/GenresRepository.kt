package com.mauscoelho.upcomingmovies.infraestruture.boundary

import com.mauscoelho.upcomingmovies.model.Genres
import rx.Observable


interface GenresRepository {
    fun getGenres(api_key: String, language: String, ids :Array<Int>) : Observable<Genres>
}