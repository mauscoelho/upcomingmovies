package com.mauscoelho.upcomingmovies.view

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.mauscoelho.upcomingmovies.MoviesApplication
import com.mauscoelho.upcomingmovies.R

class MainActivity : AppCompatActivity() {

    @Override
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        MoviesApplication.appComponent.inject(this)
    }
}
