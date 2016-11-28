package com.mauscoelho.upcomingmovies.model


data class Movie(
        var totalPages: Int,
        var currentPage : Int,
        val id: Int,
        val original_title: String,
        val poster_path: String,
        val overview: String,
        val release_date: String,
        var genres: Array<Genre>,
        val genre_ids: Array<Int>)


