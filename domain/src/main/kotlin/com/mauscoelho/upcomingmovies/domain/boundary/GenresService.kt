package com.mauscoelho.upcomingmovies.domain.boundary

import com.mauscoelho.upcomingmovies.model.Genres
import com.mauscoelho.upcomingmovies.model.Movie
import rx.Observable


interface GenresService {
    fun fetchGenres(api_key: String, language: String, genre_ids: Movie) : Observable<Movie>
    fun test(api_key: String, language: String, genre_ids: Movie) : Observable<Genres>
}