package com.mauscoelho.upcomingmovies.views.movieDetail

import com.mauscoelho.upcomingmovies.model.Movie


class MovieDetailPresenterImpl : MovieDetailPresenter {

    lateinit var movieDetailView: MovieDetailView

    override fun injectView(movieDetailView: MovieDetailView, movie: Movie) {
        this.movieDetailView = movieDetailView
        bind(movie)
    }

    fun bind(movie: Movie) {
        movieDetailView.setupToolbar(movie.title)
        movieDetailView.setHeaderImage(movie.backdrop_path)
        movieDetailView.setOverview(movie.overview)
        movieDetailView.setPosterImage(movie.poster_path)
        movieDetailView.setVoteAvarage(movie.vote_average)
        movieDetailView.setOriginalTitle(movie.original_title)
    }
}