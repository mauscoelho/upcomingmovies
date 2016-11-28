package com.mauscoelho.upcomingmovies.domain.boundary

import com.mauscoelho.upcomingmovies.model.UpcomingMovies
import rx.Observable


interface UpcomingMoviesService {
    fun getUpcomingMovies(api_key: String, language: String, page: Int) : Observable<UpcomingMovies>
}