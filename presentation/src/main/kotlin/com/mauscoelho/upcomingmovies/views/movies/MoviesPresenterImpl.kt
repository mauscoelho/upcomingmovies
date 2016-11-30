package com.mauscoelho.upcomingmovies.views.movies

import android.util.Log
import com.mauscoelho.upcomingmovies.BuildConfig
import com.mauscoelho.upcomingmovies.domain.boundary.GenreService
import com.mauscoelho.upcomingmovies.domain.boundary.UpcomingMoviesService
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import rx.subscriptions.CompositeSubscription

class MoviesPresenterImpl(val upcomingMoviesService: UpcomingMoviesService,
                          val language: String,
                          val compositeSubscription: CompositeSubscription,
                          val genreService: GenreService) : MoviesPresenter {

    lateinit var moviesView: MoviesView
    var currentPage = 0
    var totalPages = 1
    var isSearch = false

    override fun injectView(moviesView: MoviesView) {
        this.moviesView = moviesView
    }

    override fun firstLoadMovies() {
        val subscription = genreService.loadGenres(BuildConfig.API_KEY, language)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnCompleted {
                    loadMovies()
                }.subscribe { }
        compositeSubscription.add(subscription)
    }

    override fun loadMovies() {
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

    override fun clearSubscriptions() {
        compositeSubscription.clear()
    }

    override fun search(newText: String) {
        when (newText) {
            "" -> resetMovies()
            else -> searchMovie(newText)
        }
    }

    private fun resetMovies() {
        isSearch = false
        currentPage = 0
        totalPages = 1
        moviesView.clearMovies()
        moviesView.showLoading()
        loadMovies()
    }

    private fun searchMovie(newText: String) {
        isSearch = true
        moviesView.clearMovies()
        moviesView.showLoading()
        Log.i("teste", "search $newText isSearch $isSearch")
    }
}