package com.mauscoelho.upcomingmovies.views.movies

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.view.View
import com.mauscoelho.upcomingmovies.MoviesApplication
import com.mauscoelho.upcomingmovies.R
import com.mauscoelho.upcomingmovies.model.Movie
import com.mauscoelho.upcomingmovies.views.helper.InfiniteScrollListener
import com.mauscoelho.upcomingmovies.views.movies.adapters.MoviesAdapter
import kotlinx.android.synthetic.main.activity_movies.*
import javax.inject.Inject


class MoviesActivity : AppCompatActivity(), MoviesView {

    @Inject lateinit var moviesPresenter: MoviesPresenter
    @Inject lateinit var moviesAdapter: MoviesAdapter

    @Override
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movies)
        MoviesApplication.appComponent.inject(this)
        moviesPresenter.injectView(this)
        initialize()
    }

    private fun initialize() {
        rv_movies.adapter = moviesAdapter
        rv_movies.addOnScrollListener(InfiniteScrollListener({ moviesPresenter.loadMovies() }, rv_movies.layoutManager as GridLayoutManager))
        moviesPresenter.loadMovies()
    }

    override fun addMovie(movie: Movie) {
        moviesAdapter.addMovie(movie)
    }

    override fun hideLoading() {
        progress_bar.visibility = View.GONE
    }

    override fun onDestroy() {
        super.onDestroy()
        moviesPresenter.clearSubscriptions()
    }
}
