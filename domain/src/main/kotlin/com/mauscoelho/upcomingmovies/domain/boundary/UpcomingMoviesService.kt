package com.mauscoelho.upcomingmovies.domain.boundary

import com.mauscoelho.upcomingmovies.model.Movie
import rx.Observable


interface UpcomingMoviesService {
    fun getUpcomingMovies(apiKey: String, language: String, page: Int): Observable<Movie>
    fun getMovie(movieId: Int, apiKey: String, language: String): Observable<Movie>
    fun searchMovies(query: String, apiKey: String, language: String, page: Int): Observable<Movie>
}