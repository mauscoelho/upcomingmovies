package com.mauscoelho.upcomingmovies.views.movies

interface MoviesPresenter {
    fun injectView(moviesView: MoviesView)
    fun loadMovies(page: Int = 1)
}