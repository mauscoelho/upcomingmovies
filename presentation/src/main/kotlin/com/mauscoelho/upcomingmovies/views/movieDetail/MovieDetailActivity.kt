package com.mauscoelho.upcomingmovies.views.movieDetail

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import com.bumptech.glide.Glide
import com.mauscoelho.upcomingmovies.BuildConfig
import com.mauscoelho.upcomingmovies.MoviesApplication
import com.mauscoelho.upcomingmovies.R
import com.mauscoelho.upcomingmovies.model.Movie
import kotlinx.android.synthetic.main.activity_movie_detail.*
import kotlinx.android.synthetic.main.activity_movie_detail_card_poster.*
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

    override fun onOptionsItemSelected(menuItem: MenuItem): Boolean {
        when (menuItem.itemId) {
            android.R.id.home -> {
                onBackPressed()
            }
            else -> return super.onOptionsItemSelected(menuItem)
        }
        return super.onOptionsItemSelected(menuItem)
    }

    override fun setupToolbar(title: String) {
        toolbar.title = title
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
    }

    override fun setHeaderImage(imagePath: String?) {
        Glide.with(this).load(BuildConfig.API_IMAGE_TMDB + imagePath).centerCrop().crossFade().into(movie_detail_header_image)
    }

    override fun setOverview(overview: String) {
        activity_movie_detail_overview.text = overview
    }

    override fun setPosterImage(posterPath: String?) {
        Glide.with(this).load(BuildConfig.API_IMAGE_TMDB + posterPath).centerCrop().crossFade().into(activity_movie_detail_poster)
    }

    override fun setVoteAvarage(voteAverage: Double) {
        activity_movie_detail_vote_average.text = voteAverage.toString()
    }

    override fun setOriginalTitle(originalTitle: String) {
        activity_movie_detail_original_title.text = originalTitle
    }

    override fun setReleaseDate(releaseDate: String) {
        activity_movie_detail_release_date.text = releaseDate
    }

    override fun setOriginalLanguage(originalLanguage: String?) {
        activity_movie_detail_original_language.text = originalLanguage
    }

    override fun setRuntime(runtime: String?) {
        activity_movie_detail_runtime.text = runtime
    }

}
