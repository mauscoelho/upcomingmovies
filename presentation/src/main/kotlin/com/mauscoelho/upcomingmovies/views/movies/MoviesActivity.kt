package com.mauscoelho.upcomingmovies.views.movies

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.mauscoelho.upcomingmovies.MoviesApplication
import com.mauscoelho.upcomingmovies.R
import com.mauscoelho.upcomingmovies.model.Movie
import kotlinx.android.synthetic.main.activity_movies.*
import javax.inject.Inject

class MoviesActivity : AppCompatActivity(), MoviesView {
    @Inject lateinit var moviesPresenter : MoviesPresenter
    @Inject lateinit var moviesAdapter : MoviesAdapter

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
        moviesPresenter.loadMovies()
    }

    override fun addMovie(movie: Movie) {
        moviesAdapter.addMovie(movie)
    }
}
