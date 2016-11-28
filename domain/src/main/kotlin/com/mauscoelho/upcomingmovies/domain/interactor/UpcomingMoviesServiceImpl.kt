package com.mauscoelho.upcomingmovies.domain.interactor

import com.mauscoelho.upcomingmovies.domain.boundary.UpcomingMoviesService
import com.mauscoelho.upcomingmovies.infraestruture.UpcomingMoviesRepository
import com.mauscoelho.upcomingmovies.model.UpcomingMovies
import rx.Observable


class UpcomingMoviesServiceImpl(val upcomingMoviesRepository: UpcomingMoviesRepository) : UpcomingMoviesService{
    override fun getUpcomingMovies(api_key: String, language: String, page: Int): Observable<UpcomingMovies> {
        return upcomingMoviesRepository.getUpcomingMovies(api_key, language, page)
    }
}