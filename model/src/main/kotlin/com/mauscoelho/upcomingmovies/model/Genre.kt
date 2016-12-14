package com.mauscoelho.upcomingmovies.model

import android.os.Parcel
import android.os.Parcelable
import java.io.Serializable


data class Genre(
        val id: Int,
        val name: String) : Parcelable, Serializable {
    companion object {
        @JvmField val CREATOR: Parcelable.Creator<Genre> = object : Parcelable.Creator<Genre> {
            override fun createFromParcel(source: Parcel): Genre = Genre(source)
            override fun newArray(size: Int): Array<Genre?> = arrayOfNulls(size)
        }
    }

    constructor(source: Parcel) : this(source.readInt(), source.readString())

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel?, flags: Int) {
        dest?.writeInt(id)
        dest?.writeString(name)
    }
}