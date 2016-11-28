package com.mauscoelho.upcomingmovies.views.movies

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.mauscoelho.upcomingmovies.MoviesApplication
import com.mauscoelho.upcomingmovies.R
import javax.inject.Inject

class MoviesActivity : AppCompatActivity(), MoviesView {

    @Inject lateinit var moviesPresenter : MoviesPresenter

    @Override
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        MoviesApplication.appComponent.inject(this)
        moviesPresenter.injectView(this)
        initialize()
    }

    private fun initialize() {
        moviesPresenter.loadMovies()
    }
}
