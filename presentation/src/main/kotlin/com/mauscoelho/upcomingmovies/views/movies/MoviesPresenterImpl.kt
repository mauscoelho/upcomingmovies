package com.mauscoelho.upcomingmovies.views.movies

import com.mauscoelho.upcomingmovies.BuildConfig
import com.mauscoelho.upcomingmovies.domain.boundary.UpcomingMoviesService
import okhttp3.internal.Internal.logger
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class MoviesPresenterImpl(val upcomingMoviesService: UpcomingMoviesService) : MoviesPresenter {
    lateinit var moviesView: MoviesView
    val language = "en-US"
    var currentPage = 0
    var totalPages = 1

    override fun injectView(moviesView: MoviesView) {
        this.moviesView = moviesView
    }

    override fun loadMovies() {
        if (currentPage < totalPages)
            upcomingMoviesService.getUpcomingMovies(BuildConfig.API_KEY, language, currentPage + 1)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({
                        logger.info("page ${it.page}")
                        logger.info("total_pages ${it.total_pages}")
                        currentPage = it.page
                        totalPages = it.total_pages
                        it.results.map {
                            logger.info("movie ${it.original_title}")
                            moviesView.addMovie(it)
                        }
                    }, {
                        logger.info("error:" + it.message)
                    })
    }
}