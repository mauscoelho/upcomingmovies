package com.mauscoelho.upcomingmovies.dagger

import com.mauscoelho.upcomingmovies.views.movies.MoviesActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(MainModule::class))
interface AppComponent {
    fun inject(moviesActivity: MoviesActivity)
}