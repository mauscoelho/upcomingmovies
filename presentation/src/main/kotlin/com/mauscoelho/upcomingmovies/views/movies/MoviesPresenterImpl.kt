package com.mauscoelho.upcomingmovies.views.movies

import android.util.Log
import com.mauscoelho.upcomingmovies.BuildConfig
import com.mauscoelho.upcomingmovies.domain.boundary.UpcomingMoviesService
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class MoviesPresenterImpl(val upcomingMoviesService: UpcomingMoviesService) : MoviesPresenter{
    lateinit var moviesView : MoviesView
    val language = "en-US"
    var currentPage = 1
    var totalPages = 1

    override fun injectView(moviesView: MoviesView) {
        this.moviesView = moviesView
    }

    override fun loadMovies(page: Int) {
        upcomingMoviesService.getUpcomingMovies(BuildConfig.API_KEY, language, page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    currentPage = it.page
                    totalPages = it.total_pages
                    it.results.map {
                        Log.i(MoviesPresenterImpl::class.simpleName, "movie ${it.original_title}")
                    }
                }, {
                    Log.i(MoviesPresenterImpl::class.simpleName, "error:" + it.message)
                })
    }
}