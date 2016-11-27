package com.mauscoelho.upcomingmovies.dagger

import android.content.Context
import com.mauscoelho.upcomingmovies.BuildConfig
import com.mauscoelho.upcomingmovies.MoviesApplication
import com.mauscoelho.upcomingmovies.domain.boundary.UpcomingMoviesService
import com.mauscoelho.upcomingmovies.domain.interactor.UpcomingMoviesServiceImpl
import com.mauscoelho.upcomingmovies.infraestruture.UpcomingMoviesRepository
import com.mauscoelho.upcomingmovies.infraestruture.interactor.UpcomingMoviesRepositoryImpl
import com.mauscoelho.upcomingmovies.infraestruture.network.TmdbNetwork
import com.mauscoelho.upcomingmovies.views.movies.MoviesPresenter
import com.mauscoelho.upcomingmovies.views.movies.MoviesPresenterImpl
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class MainModule(val application: MoviesApplication) {

    @Provides
    @Singleton
    @ForApplication
    fun provideApplicationContext(): Context {
        return application
    }

    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
                .baseUrl(BuildConfig.API_TMDB)
                .baseUrl(BuildConfig.API_IMAGE_TMDB)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create()).client(OkHttpClient())
                .build()
    }

    @Provides
    fun provideMoviesPresenter() : MoviesPresenter{
        return MoviesPresenterImpl()
    }

    @Provides
    fun provideUpcomingMoviesService() : UpcomingMoviesService {
        return UpcomingMoviesServiceImpl(provideUpcomingMoviesRepository())
    }

    @Provides
    fun provideUpcomingMoviesRepository() : UpcomingMoviesRepository {
        return UpcomingMoviesRepositoryImpl(provideTmdbNetwork(provideRetrofit()))
    }

    @Provides
    fun provideTmdbNetwork(retrofit: Retrofit) : TmdbNetwork {
        return retrofit.create(TmdbNetwork::class.java)
    }


}