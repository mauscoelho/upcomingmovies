package com.mauscoelho.upcomingmovies.infraestruture.network

import com.mauscoelho.upcomingmovies.model.UpcomingMovies
import retrofit2.http.GET
import retrofit2.http.Path
import rx.Observable


interface TmdbNetwork {
    @GET("movie/upcoming")
    fun getGithubUser(@Path("api_key") api_key : String, @Path("language") language : String) : Observable<UpcomingMovies>
}