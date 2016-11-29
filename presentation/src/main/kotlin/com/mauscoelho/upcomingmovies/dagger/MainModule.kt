package com.mauscoelho.upcomingmovies.dagger

import android.content.Context
import com.mauscoelho.upcomingmovies.BuildConfig
import com.mauscoelho.upcomingmovies.MoviesApplication
import com.mauscoelho.upcomingmovies.domain.boundary.GenresService
import com.mauscoelho.upcomingmovies.domain.boundary.UpcomingMoviesService
import com.mauscoelho.upcomingmovies.domain.interactor.GenreServiceImpl
import com.mauscoelho.upcomingmovies.domain.interactor.UpcomingMoviesServiceImpl
import com.mauscoelho.upcomingmovies.infraestruture.UpcomingMoviesRepository
import com.mauscoelho.upcomingmovies.infraestruture.interactor.UpcomingMoviesRepositoryImpl
import com.mauscoelho.upcomingmovies.infraestruture.network.TmdbNetwork
import com.mauscoelho.upcomingmovies.views.movies.MoviesPresenter
import com.mauscoelho.upcomingmovies.views.movies.MoviesPresenterImpl
import com.mauscoelho.upcomingmovies.views.movies.adapters.MoviesAdapter
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
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
    fun provideokHttp(): OkHttpClient {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.HEADERS
        return OkHttpClient.Builder().addInterceptor(interceptor).build()
    }

    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
                .baseUrl(BuildConfig.API_TMDB)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(provideokHttp())
                .build()
    }

    @Provides
    fun provideMoviesPresenter(): MoviesPresenter {
        return MoviesPresenterImpl(provideUpcomingMoviesService(), provideGenresService(), provideLanguage())
    }

    @Provides
    fun provideUpcomingMoviesService(): UpcomingMoviesService {
        return UpcomingMoviesServiceImpl(provideUpcomingMoviesRepository(), provideGenresService())
    }

    @Provides
    fun provideGenresService(): GenresService {
        return GenreServiceImpl()
    }

    @Provides
    fun provideUpcomingMoviesRepository(): UpcomingMoviesRepository {
        return UpcomingMoviesRepositoryImpl(provideTmdbNetwork(provideRetrofit()))
    }

    @Provides
    fun provideTmdbNetwork(retrofit: Retrofit): TmdbNetwork {
        return retrofit.create(TmdbNetwork::class.java)
    }

    @Provides
    fun provideMoviesAdapter(): MoviesAdapter {
        return MoviesAdapter()
    }

    @Provides
    fun provideLanguage() = "en-US"

}