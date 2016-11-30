package com.mauscoelho.upcomingmovies.views.movies

import android.os.Bundle
import android.support.v4.view.MenuItemCompat
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.SearchView
import android.view.Menu
import android.view.View
import com.mauscoelho.upcomingmovies.MoviesApplication
import com.mauscoelho.upcomingmovies.R
import com.mauscoelho.upcomingmovies.model.Movie
import com.mauscoelho.upcomingmovies.views.helper.InfiniteScrollListener
import com.mauscoelho.upcomingmovies.views.movies.adapters.MoviesAdapter
import kotlinx.android.synthetic.main.activity_movies.*
import javax.inject.Inject




class MoviesActivity : AppCompatActivity(), MoviesView, SearchView.OnQueryTextListener {

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

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_movies, menu)
        val searchItem = menu?.findItem(R.id.action_search)
        val searchView = MenuItemCompat.getActionView(searchItem) as SearchView
        searchView.setOnQueryTextListener(this)
        return true
    }

    private fun initialize() {
        rv_movies.adapter = moviesAdapter
        rv_movies.addOnScrollListener(InfiniteScrollListener({ moviesPresenter.loadMovies() }, rv_movies.layoutManager as GridLayoutManager))
        moviesPresenter.firstLoadMovies()
    }

    override fun addMovie(movie: Movie) {
        moviesAdapter.addMovie(movie)
    }

    override fun hideLoading() {
        progress_bar.visibility = View.GONE
    }

    override fun showLoading() {
        progress_bar.visibility = View.VISIBLE
    }

    override fun onDestroy() {
        super.onDestroy()
        moviesPresenter.clearSubscriptions()
    }

    override fun clearMovies() {
        moviesAdapter.clear()
    }

    override fun onQueryTextSubmit(query: String): Boolean = false

    override fun onQueryTextChange(newText: String): Boolean {
        moviesPresenter.search(newText)
        return false
    }
}
