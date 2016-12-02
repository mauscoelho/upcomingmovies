package com.mauscoelho.upcomingmovies.domain.interactor

import com.mauscoelho.upcomingmovies.domain.boundary.GenreService
import com.mauscoelho.upcomingmovies.infrastructure.boundary.GenreRepository
import com.mauscoelho.upcomingmovies.model.Genres
import rx.Observable


class GenreServiceImpl(val genreRepository: GenreRepository) : GenreService {
    override fun loadGenres(apiKey: String, language: String): Observable<Genres> {
        return genreRepository.loadGenres(apiKey, language)
    }
}