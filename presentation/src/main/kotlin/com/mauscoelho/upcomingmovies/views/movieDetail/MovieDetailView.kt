package com.mauscoelho.upcomingmovies.views.movieDetail


interface MovieDetailView {
    fun setTitle(title:String)
    fun setHeaderImage(imagePath:String)
    fun setOverview(overview:String)
}