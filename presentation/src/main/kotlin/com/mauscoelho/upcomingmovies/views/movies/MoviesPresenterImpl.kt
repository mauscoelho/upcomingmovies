package com.mauscoelho.upcomingmovies.views.movies

import android.util.Log

class MoviesPresenterImpl : MoviesPresenter{

    lateinit var moviesView : MoviesView

    override fun injectView(moviesView: MoviesView) {
        this.moviesView = moviesView
    }

    override fun loadMovies(page: Int) {
        Log.i("MoviesPresenterImpl", "Call load movies")
    }
}