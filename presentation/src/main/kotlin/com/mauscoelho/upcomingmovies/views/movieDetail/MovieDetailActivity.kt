package com.mauscoelho.upcomingmovies.views.movieDetail

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.mauscoelho.upcomingmovies.BuildConfig
import com.mauscoelho.upcomingmovies.MoviesApplication
import com.mauscoelho.upcomingmovies.R
import com.mauscoelho.upcomingmovies.model.Movie
import kotlinx.android.synthetic.main.activity_movie_detail.*
import kotlinx.android.synthetic.main.activity_movie_detail_content.*
import javax.inject.Inject

class MovieDetailActivity : AppCompatActivity(), MovieDetailView {

    @Inject lateinit var movieDetailPresenter: MovieDetailPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)
        MoviesApplication.appComponent.inject(this)
        val movie = intent.getParcelableExtra<Movie>("movie")
        movieDetailPresenter.injectView(this, movie)
    }

    override fun setTitle(title: String) {
        toolbar.title = title
        setSupportActionBar(toolbar)
    }

    override fun setHeaderImage(imagePath: String) {
        Glide.with(this).load(BuildConfig.API_IMAGE_TMDB + imagePath).centerCrop().crossFade().into(movie_detail_header_image)
    }

    override fun setOverview(overview: String) {
        activity_movie_detail_overview.text = overview
    }
}
