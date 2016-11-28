package com.mauscoelho.upcomingmovies.views.movies.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.mauscoelho.upcomingmovies.BuildConfig
import com.mauscoelho.upcomingmovies.R
import com.mauscoelho.upcomingmovies.model.Movie
import com.mauscoelho.upcomingmovies.views.movies.adapters.GenresAdapter
import kotlinx.android.synthetic.main.item_movie.view.*


class MoviesAdapter : RecyclerView.Adapter<MoviesAdapter.ItemViewHolder>() {
    var movies = mutableListOf<Movie>()

    override fun onBindViewHolder(holder: ItemViewHolder?, position: Int) {
        holder?.bind(movies.get(position))
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ItemViewHolder {
        return ItemViewHolder(LayoutInflater.from(parent!!.context).inflate(R.layout.item_movie, parent, false))
    }

    fun addMovie(movie: Movie) {
        movies.add(movie)
        this.notifyDataSetChanged()
    }

    class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: Movie) = with(itemView) {
            item_movie_txt.text = item.original_title
            item_movie_release_date.text = item.release_date
            Glide.with(itemView.context).load(BuildConfig.API_IMAGE_TMDB + item.poster_path).centerCrop().crossFade().into(item_movie_poster)
            val genreAdapter = GenresAdapter()
            rv_genres.adapter = genreAdapter
            item.genres.map { genreAdapter.addGenre(it) }
        }
    }
}