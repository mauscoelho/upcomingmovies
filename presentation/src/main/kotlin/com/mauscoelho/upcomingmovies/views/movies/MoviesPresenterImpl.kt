package com.mauscoelho.upcomingmovies.views.movies

import com.mauscoelho.upcomingmovies.BuildConfig
import com.mauscoelho.upcomingmovies.domain.boundary.GenresService
import com.mauscoelho.upcomingmovies.domain.boundary.UpcomingMoviesService
import okhttp3.internal.Internal.logger
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import rx.subscriptions.CompositeSubscription

class MoviesPresenterImpl(val upcomingMoviesService: UpcomingMoviesService,
                          val genresService: GenresService,
                          val language: String) : MoviesPresenter {

    lateinit var moviesView: MoviesView
    var currentPage = 0
    var totalPages = 1
    var compositeSubscription = CompositeSubscription()

    override fun injectView(moviesView: MoviesView) {
        this.moviesView = moviesView
    }

    override fun loadMovies() {
        if (currentPage < totalPages) {
            val subscription = upcomingMoviesService.getUpcomingMovies(BuildConfig.API_KEY, language, currentPage + 1)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .doOnCompleted { moviesView.hideLoading() }
                    .subscribe({
                        currentPage = it.currentPage
                        totalPages = it.totalPages
                        moviesView.addMovie(it)
                    }, {
                        logger.info("error:" + it.message)
                    })
            compositeSubscription.add(subscription)
        }
    }

    override fun clearSubscriptions() {
        compositeSubscription.clear()
    }

}