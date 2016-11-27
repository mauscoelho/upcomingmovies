package com.mauscoelho.upcomingmovies.infraestruture

import com.mauscoelho.upcomingmovies.model.UpcomingMovies
import rx.Observable

interface UpcomingMoviesRepository {

    fun getUpcomingMovies() : Observable<UpcomingMovies>

}
