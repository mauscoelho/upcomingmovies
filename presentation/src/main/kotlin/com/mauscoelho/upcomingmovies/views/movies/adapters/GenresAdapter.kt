package com.mauscoelho.upcomingmovies.views.movies.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mauscoelho.upcomingmovies.R
import com.mauscoelho.upcomingmovies.model.Genre
import kotlinx.android.synthetic.main.item_genre.view.*


class GenresAdapter : RecyclerView.Adapter<GenresAdapter.ItemViewHolder>() {
    var genres = mutableListOf<Genre>()

    override fun onBindViewHolder(holder: ItemViewHolder?, position: Int) {
        holder?.bind(genres.get(position))
    }

    override fun getItemCount(): Int {
        return genres.size
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ItemViewHolder {
        return ItemViewHolder(LayoutInflater.from(parent!!.context).inflate(R.layout.item_genre, parent, false))
    }

    fun addGenre(genre: Genre) {
        genres.add(genre)
        this.notifyDataSetChanged()
    }

    class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: Genre) = with(itemView) {
            item_genre_txt.text = item.name
        }
    }
}