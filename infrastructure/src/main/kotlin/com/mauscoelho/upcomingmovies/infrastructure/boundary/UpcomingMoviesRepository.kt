package com.mauscoelho.upcomingmovies.infrastructure.boundary

import com.mauscoelho.upcomingmovies.model.Movie
import com.mauscoelho.upcomingmovies.model.UpcomingMovies
import rx.Observable

interface UpcomingMoviesRepository {
    fun getUpcomingMovies(api_key: String, language: String, page: Int) : Observable<UpcomingMovies>
    fun getMovie(movieId: Int, api_key: String, language: String) : Observable<Movie>
}
