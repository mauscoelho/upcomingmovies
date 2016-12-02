package com.mauscoelho.upcomingmovies.views.movieDetail

import com.mauscoelho.upcomingmovies.model.Genre


interface MovieDetailView {
    fun setupToolbar(title: String)
    fun setHeaderImage(imagePath: String?)
    fun setOverview(overview: String)
    fun setPosterImage(posterPath: String?)
    fun setVoteAvarage(voteAverage: Double)
    fun setOriginalTitle(originalTitle: String)
    fun setReleaseDate(releaseDate: String)
    fun setOriginalLanguage(originalLanguage: String?)
    fun addGenre(genre: Genre)
}