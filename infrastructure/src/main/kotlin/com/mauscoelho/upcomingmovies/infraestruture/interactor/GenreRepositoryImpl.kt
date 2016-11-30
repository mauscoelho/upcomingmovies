package com.mauscoelho.upcomingmovies.infraestruture.interactor

import com.mauscoelho.upcomingmovies.infraestruture.boundary.GenreRepository
import com.mauscoelho.upcomingmovies.model.Genre
import rx.Observable

class GenreRepositoryImpl : GenreRepository{
    override fun getGenres(genreIds: List<Int>, api_key: String, language: String): Observable<List<Genre>> {

        return Observable.just(listOf(Genre(1,"Comedy")))
    }
}