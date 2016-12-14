package com.mauscoelho.upcomingmovies.infrastructure.boundary

import com.mauscoelho.upcomingmovies.model.Movie
import com.mauscoelho.upcomingmovies.model.UpcomingMovies
import rx.Observable

interface UpcomingMoviesRepository {
    fun getUpcomingMovies(apiKey: String, language: String, page: Int) : Observable<UpcomingMovies>
    fun getMovie(movieId: Int, apiKey: String, language: String) : Observable<Movie>
}
