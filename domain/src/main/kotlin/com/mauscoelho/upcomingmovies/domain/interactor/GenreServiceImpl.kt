package com.mauscoelho.upcomingmovies.domain.interactor

import com.mauscoelho.upcomingmovies.domain.boundary.GenresService
import com.mauscoelho.upcomingmovies.model.Genre
import rx.Observable


class GenreServiceImpl : GenresService{
    override fun getGenres(api_key: String, language: String): Observable<Genre> {
        return Observable.empty()
    }
}