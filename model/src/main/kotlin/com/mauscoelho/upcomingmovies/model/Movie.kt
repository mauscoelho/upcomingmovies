package com.mauscoelho.upcomingmovies.model

import android.os.Parcel
import android.os.Parcelable
import java.util.*


data class Movie(
        var totalPages: Int,
        var currentPage : Int,
        val id: Int,
        val title: String,
        val original_title: String,
        val poster_path: String?,
        val backdrop_path: String?,
        var overview: String,
        val release_date: String,
        val vote_average: Double,
        var genres: List<Genre>,
        val genre_ids: List<Int>,
        val original_language: String?,
        val popularity : String?,
        val runtime : String?,
        val status : String?,
        val vote_count : String?) : Parcelable {
    companion object {
        @JvmField val CREATOR: Parcelable.Creator<Movie> = object : Parcelable.Creator<Movie> {
            override fun createFromParcel(source: Parcel): Movie = Movie(source)
            override fun newArray(size: Int): Array<Movie?> = arrayOfNulls(size)
        }
    }

    constructor(source: Parcel) : this(
            source.readInt(),
            source.readInt(),
            source.readInt(),
            source.readString(),
            source.readString(),
            source.readString(),
            source.readString(),
            source.readString(),
            source.readString(),
            source.readDouble(),
            source.createTypedArrayList(Genre.CREATOR),
            ArrayList<Int>().apply{ source.readList(this, Int::class.java.classLoader) },
            source.readString(),
            source.readString(),
            source.readString(),
            source.readString(),
            source.readString())

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel?, flags: Int) {
        dest?.writeInt(totalPages)
        dest?.writeInt(currentPage)
        dest?.writeInt(id)
        dest?.writeString(title)
        dest?.writeString(original_title)
        dest?.writeString(poster_path)
        dest?.writeString(backdrop_path)
        dest?.writeString(overview)
        dest?.writeString(release_date)
        dest?.writeDouble(vote_average)
        dest?.writeTypedList(genres)
        dest?.writeList(genre_ids)
        dest?.writeString(original_language)
        dest?.writeString(popularity)
        dest?.writeString(runtime)
        dest?.writeString(status)
        dest?.writeString(vote_count)
    }
}




