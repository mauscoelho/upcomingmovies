package com.mauscoelho.upcomingmovies.views.movies

import android.util.Log
import com.mauscoelho.upcomingmovies.BuildConfig
import com.mauscoelho.upcomingmovies.domain.boundary.UpcomingMoviesService
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class MoviesPresenterImpl(val upcomingMoviesService: UpcomingMoviesService) : MoviesPresenter{

    lateinit var moviesView : MoviesView

    override fun injectView(moviesView: MoviesView) {
        this.moviesView = moviesView
    }

    override fun loadMovies(page: Int) {
        Log.i("MoviesPresenterImpl", "Call load movies " + BuildConfig.API_KEY)
        upcomingMoviesService.getUpcomingMovies(BuildConfig.API_KEY, "en-US")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    Log.i("MoviesPresenterImpl", "page: " + it.page + " count: " + it.results.size)
                }, {
                    Log.i("MoviesPresenterImpl", "error:" + it.message)
                })
    }
}