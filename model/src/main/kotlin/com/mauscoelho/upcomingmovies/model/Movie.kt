package com.mauscoelho.upcomingmovies.model

import android.os.Parcel
import android.os.Parcelable
import java.util.*


data class Movie(
        var totalPages: Int,
        var currentPage : Int,
        val id: Int,
        val original_title: String,
        val poster_path: String,
        var overview: String,
        val release_date: String,
        var genres: List<Genre>,
        val genre_ids: List<Int>) : Parcelable {
    companion object {
        @JvmField val CREATOR: Parcelable.Creator<Movie> = object : Parcelable.Creator<Movie> {
            override fun createFromParcel(source: Parcel): Movie = Movie(source)
            override fun newArray(size: Int): Array<Movie?> = arrayOfNulls(size)
        }
    }

    constructor(source: Parcel) : this(source.readInt(), source.readInt(), source.readInt(), source.readString(), source.readString(), source.readString(), source.readString(), ArrayList<Genre>().apply{ source.readList(this, Genre::class.java.classLoader) }, ArrayList<Int>().apply{ source.readList(this, Int::class.java.classLoader) })

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel?, flags: Int) {
        dest?.writeInt(totalPages)
        dest?.writeInt(currentPage)
        dest?.writeInt(id)
        dest?.writeString(original_title)
        dest?.writeString(poster_path)
        dest?.writeString(overview)
        dest?.writeString(release_date)
        dest?.writeList(genres)
        dest?.writeList(genre_ids)
    }
}


