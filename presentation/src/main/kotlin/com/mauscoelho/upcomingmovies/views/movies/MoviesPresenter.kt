package com.mauscoelho.upcomingmovies.views.movies

interface MoviesPresenter {
    fun injectView(moviesView: MoviesView)
    fun loadMovies()
    fun clearSubscriptions()
    fun firstLoadMovies()
    fun search(newText: String)
}