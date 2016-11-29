package com.mauscoelho.upcomingmovies.domain.interactor

import com.mauscoelho.upcomingmovies.domain.boundary.GenresService
import com.mauscoelho.upcomingmovies.infraestruture.boundary.GenresRepository
import com.mauscoelho.upcomingmovies.model.Genres
import com.mauscoelho.upcomingmovies.model.Movie
import rx.Observable
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers


class GenreServiceImpl(val genresRepository: GenresRepository) : GenresService {
    override fun test(api_key: String, language: String, movie: Movie): Observable<Genres> {
        return genresRepository.getGenres(api_key,language, movie.genre_ids)
    }

    override fun fetchGenres(api_key: String, language: String, movie: Movie): Observable<Movie> {
        return genresRepository.getGenres(api_key, language, movie.genre_ids)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .flatMap { genres ->
                    movie.genres = genres.genres
                    Observable.just(movie)
                }
    }
}