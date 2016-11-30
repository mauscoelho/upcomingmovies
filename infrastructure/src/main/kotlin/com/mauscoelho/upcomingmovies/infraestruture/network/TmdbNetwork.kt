package com.mauscoelho.upcomingmovies.infraestruture.network

import com.mauscoelho.upcomingmovies.model.Genres
import com.mauscoelho.upcomingmovies.model.Movie
import com.mauscoelho.upcomingmovies.model.UpcomingMovies
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import rx.Observable


interface TmdbNetwork {
    @GET("movie/upcoming")
    fun getUpcomingMovies(@Query("api_key") api_key : String, @Query("language") language : String, @Query("page") page: Int) : Observable<UpcomingMovies>

    @GET("movie/{id}")
    fun getMovie(@Path("id") movieId: Int, @Query("api_key") api_key : String, @Query("language") language : String) : Observable<Movie>

    @GET("genre/movie/list")
    fun getGenres(@Query("api_key") api_key : String, @Query("language") language : String) : Observable<Genres>

}