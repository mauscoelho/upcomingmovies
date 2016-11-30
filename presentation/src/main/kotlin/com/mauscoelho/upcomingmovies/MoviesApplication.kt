package com.mauscoelho.upcomingmovies

import android.app.Application
import com.mauscoelho.upcomingmovies.dagger.AppComponent
import com.mauscoelho.upcomingmovies.dagger.DaggerAppComponent
import com.mauscoelho.upcomingmovies.dagger.MainModule
import io.paperdb.Paper


class MoviesApplication : Application() {
    companion object {
        @JvmStatic lateinit var appComponent: AppComponent
    }
    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder().mainModule(MainModule(this)).build()
        Paper.init(this)
    }
}