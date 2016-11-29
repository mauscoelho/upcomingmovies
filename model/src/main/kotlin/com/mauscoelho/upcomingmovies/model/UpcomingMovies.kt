package com.mauscoelho.upcomingmovies.model


data class UpcomingMovies(
        val page: Int,
        var results: Array<Movie>,
        val total_pages: Int,
        val total_results: Int) {
}