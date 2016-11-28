package com.mauscoelho.upcomingmovies.infraestruture.network

import com.mauscoelho.upcomingmovies.model.UpcomingMovies
import retrofit2.http.GET
import retrofit2.http.Query
import rx.Observable


interface TmdbNetwork {
    @GET("movie/upcoming")
    fun getUpcomingMovies(@Query("api_key") api_key : String, @Query("language") language : String) : Observable<UpcomingMovies>
}