package com.mauscoelho.upcomingmovies.infraestruture

import com.mauscoelho.upcomingmovies.model.UpcomingMovies
import rx.Observable

interface UpcomingMoviesRepository {
    fun getUpcomingMovies(api_key: String, language: String) : Observable<UpcomingMovies>
}
