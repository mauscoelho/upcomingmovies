package com.mauscoelho.upcomingmovies.views.movieDetail

import com.mauscoelho.upcomingmovies.model.Movie


interface MovieDetailPresenter {
    fun injectView(movieDetailView: MovieDetailView, movie: Movie)
}