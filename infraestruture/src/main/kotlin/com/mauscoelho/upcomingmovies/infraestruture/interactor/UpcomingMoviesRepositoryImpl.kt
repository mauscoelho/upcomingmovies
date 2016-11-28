package com.mauscoelho.upcomingmovies.infraestruture.interactor

import android.util.Log
import com.mauscoelho.upcomingmovies.infraestruture.UpcomingMoviesRepository
import com.mauscoelho.upcomingmovies.infraestruture.network.TmdbNetwork
import com.mauscoelho.upcomingmovies.model.UpcomingMovies
import rx.Observable


class UpcomingMoviesRepositoryImpl(val tmdbNetwork: TmdbNetwork) : UpcomingMoviesRepository {
    override fun getUpcomingMovies(api_key: String, language: String): Observable<UpcomingMovies> {
        Log.i("UpcomingMoviesRepo", "apikey " + api_key)
        return tmdbNetwork.getUpcomingMovies(api_key, language)
    }
}