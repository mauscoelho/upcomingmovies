package com.mauscoelho.upcomingmovies.infraestruture

import com.mauscoelho.upcomingmovies.model.MovieResponse
import com.mauscoelho.upcomingmovies.model.UpcomingMovies
import rx.Observable

interface UpcomingMoviesRepository {
    fun getUpcomingMovies(api_key: String, language: String, page: Int) : Observable<UpcomingMovies>
    fun getMovie(movieId: Int, api_key: String, language: String) : Observable<MovieResponse>
}
