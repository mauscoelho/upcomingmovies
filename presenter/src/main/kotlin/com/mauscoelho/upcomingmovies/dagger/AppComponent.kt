package com.mauscoelho.upcomingmovies.dagger

import com.mauscoelho.upcomingmovies.view.MainActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(MainModule::class))
interface AppComponent {
    fun inject(mainActivity: MainActivity)
}