package com.mauscoelho.upcomingmovies.views.movies

import com.mauscoelho.upcomingmovies.BuildConfig
import com.mauscoelho.upcomingmovies.domain.boundary.UpcomingMoviesService
import com.mauscoelho.upcomingmovies.model.Genre
import okhttp3.internal.Internal.logger
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class MoviesPresenterImpl(val upcomingMoviesService: UpcomingMoviesService,
                          val language: String) : MoviesPresenter {

    lateinit var moviesView: MoviesView
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
                        currentPage = it.page
                        totalPages = it.total_pages
                        it.results.map {
                            it.genres = getGenres(it.genre_ids)
                            moviesView.addMovie(it)
                        }
                        moviesView.hideLoading()
                    }, {
                        logger.info("error:" + it.message)
                    })
    }

    fun getGenres(genre_ids: Array<Int>): Array<Genre> {
      return arrayOf(Genre(1, "teste"))
    }

}