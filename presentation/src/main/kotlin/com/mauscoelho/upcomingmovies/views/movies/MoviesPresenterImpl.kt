package com.mauscoelho.upcomingmovies.views.movies

import android.util.Log
import com.mauscoelho.upcomingmovies.BuildConfig
import com.mauscoelho.upcomingmovies.domain.boundary.UpcomingMoviesService
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import rx.subscriptions.CompositeSubscription
import java.util.concurrent.TimeUnit

class MoviesPresenterImpl(val upcomingMoviesService: UpcomingMoviesService,
                          val language: String,
                          val compositeSubscription: CompositeSubscription) : MoviesPresenter {

    lateinit var moviesView: MoviesView
    var currentPage = 0
    var totalPages = 1

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
                        Log.i("Error", "error: ${it.message}")
                    })
            compositeSubscription.add(subscription)
        }
    }

    override fun clearSubscriptions() {
        compositeSubscription.clear()
    }

}