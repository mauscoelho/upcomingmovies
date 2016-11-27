package com.mauscoelho.upcomingmovies.model


class UpcomingMovies(
        val page: Int,
        val results: Array<Movie>,
        val total_pages: Int,
        val total_results: Int) {
}