package com.mauscoelho.upcomingmovies.views.movieDetail

import com.mauscoelho.upcomingmovies.model.Movie


class MovieDetailPresenterImpl : MovieDetailPresenter {

    lateinit var movieDetailView: MovieDetailView

    override fun injectView(movieDetailView: MovieDetailView, movie: Movie) {
        this.movieDetailView = movieDetailView
        bind(movie)
    }

    fun bind(movie: Movie) {
        movieDetailView.setTitle(movie.title)
        movieDetailView.setHeaderImage(movie.backdrop_path)
        movieDetailView.setOverview(movie.overview)
    }
}