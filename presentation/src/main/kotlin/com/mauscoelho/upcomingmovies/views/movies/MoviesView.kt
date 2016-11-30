package com.mauscoelho.upcomingmovies.views.movies

import com.mauscoelho.upcomingmovies.model.Movie

interface MoviesView {
    fun addMovie(movie: Movie)
    fun hideLoading()
    fun clearMovies()
    fun showLoading()
}