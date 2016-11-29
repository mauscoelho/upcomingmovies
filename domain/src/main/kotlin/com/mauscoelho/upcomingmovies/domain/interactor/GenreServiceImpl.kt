package com.mauscoelho.upcomingmovies.domain.interactor

import com.mauscoelho.upcomingmovies.domain.boundary.GenresService
import com.mauscoelho.upcomingmovies.model.Genre
import com.mauscoelho.upcomingmovies.model.Movie
import rx.Observable


class GenreServiceImpl : GenresService{
    override fun fetchGenres(api_key: String, language: String, movie: Movie): Observable<Movie> {
        movie.genres = arrayOf(Genre(1, "Horror"))
        return Observable.just(movie)
    }
}