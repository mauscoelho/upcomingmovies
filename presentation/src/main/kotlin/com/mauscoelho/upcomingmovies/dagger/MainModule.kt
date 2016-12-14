package com.mauscoelho.upcomingmovies.dagger

import android.content.Context
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.mauscoelho.upcomingmovies.BuildConfig
import com.mauscoelho.upcomingmovies.MoviesApplication
import com.mauscoelho.upcomingmovies.domain.boundary.GenreService
import com.mauscoelho.upcomingmovies.domain.boundary.UpcomingMoviesService
import com.mauscoelho.upcomingmovies.domain.interactor.GenreServiceImpl
import com.mauscoelho.upcomingmovies.domain.interactor.UpcomingMoviesServiceImpl
import com.mauscoelho.upcomingmovies.infrastructure.boundary.GenreRepository
import com.mauscoelho.upcomingmovies.infrastructure.boundary.SearchRepository
import com.mauscoelho.upcomingmovies.infrastructure.boundary.UpcomingMoviesRepository
import com.mauscoelho.upcomingmovies.infrastructure.interactor.GenreRepositoryImpl
import com.mauscoelho.upcomingmovies.infrastructure.interactor.SearchRepositoryImpl
import com.mauscoelho.upcomingmovies.infrastructure.interactor.UpcomingMoviesRepositoryImpl
import com.mauscoelho.upcomingmovies.infrastructure.network.TmdbNetwork
import com.mauscoelho.upcomingmovies.views.movieDetail.MovieDetailPresenter
import com.mauscoelho.upcomingmovies.views.movieDetail.MovieDetailPresenterImpl
import com.mauscoelho.upcomingmovies.views.movies.MoviesPresenter
import com.mauscoelho.upcomingmovies.views.movies.MoviesPresenterImpl
import com.mauscoelho.upcomingmovies.views.movies.adapters.MoviesAdapter
import com.snappydb.DB
import com.snappydb.DBFactory
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.GsonConverterFactory
import retrofit2.Retrofit
import retrofit2.RxJavaCallAdapterFactory
import rx.Scheduler
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import rx.subscriptions.CompositeSubscription
import javax.inject.Singleton


@Module
class MainModule(val application: MoviesApplication) {

    @Provides
    @Singleton
    @ForApplication
    fun provideApplicationContext(): Context = application

    @Provides
    fun provideokHttp(): OkHttpClient {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.HEADERS
        return OkHttpClient.Builder().addInterceptor(interceptor).build()
    }

    @Provides
    fun provideRetrofit(): Retrofit =
            Retrofit.Builder()
                    .baseUrl(BuildConfig.API_TMDB)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .client(provideokHttp())
                    .build()

    @Provides
    fun provideMoviesPresenter(): MoviesPresenter = MoviesPresenterImpl(provideUpcomingMoviesService(), provideLanguage(), provideCompositeSubscription(), provideGenreService())

    @Provides
    fun provideMovieDetailPresenter(): MovieDetailPresenter = MovieDetailPresenterImpl()

    @Provides
    fun provideCompositeSubscription(): CompositeSubscription = CompositeSubscription()

    @Provides
    fun provideUpcomingMoviesService(): UpcomingMoviesService {
        return UpcomingMoviesServiceImpl(provideUpcomingMoviesRepository(), provideGenreRepository(), provideSearchRepository(), provideIoScheduler(), provideMainThreadScheduler())
    }

    @Provides
    fun provideGenreService(): GenreService = GenreServiceImpl(provideGenreRepository())

    @Provides
    fun provideUpcomingMoviesRepository(): UpcomingMoviesRepository = UpcomingMoviesRepositoryImpl(provideTmdbNetwork(provideRetrofit()))

    @Provides
    fun provideGenreRepository(): GenreRepository = GenreRepositoryImpl(provideTmdbNetwork(provideRetrofit()), provideGenresCollection(), provideSnappyDb(provideApplicationContext()), provideMapper())

    @Provides
    fun provideSearchRepository(): SearchRepository = SearchRepositoryImpl(provideTmdbNetwork(provideRetrofit()))

    @Provides
    fun provideTmdbNetwork(retrofit: Retrofit): TmdbNetwork = retrofit.create(TmdbNetwork::class.java)

    @Singleton
    @Provides
    fun provideSnappyDb(context: Context): DB = DBFactory.open(context)

    @Provides
    fun provideMapper(): ObjectMapper = jacksonObjectMapper()

    @Provides
    fun provideMoviesAdapter(): MoviesAdapter = MoviesAdapter()

    @Provides
    fun provideLanguage() = "en-US"

    @Provides
    fun provideGenresCollection() = "genres"

    @Provides
    @Singleton
    fun provideMainThreadScheduler(): Scheduler = AndroidSchedulers.mainThread()

    @Provides
    @Singleton
    fun provideIoScheduler(): Scheduler = Schedulers.io()

    @Provides
    @Singleton
    fun provideCompScheduler(): Scheduler = Schedulers.computation()

}