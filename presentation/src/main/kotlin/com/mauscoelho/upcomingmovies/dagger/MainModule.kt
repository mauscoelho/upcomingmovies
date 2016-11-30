package com.mauscoelho.upcomingmovies.dagger

import android.content.Context
import com.mauscoelho.upcomingmovies.BuildConfig
import com.mauscoelho.upcomingmovies.MoviesApplication
import com.mauscoelho.upcomingmovies.domain.boundary.GenreService
import com.mauscoelho.upcomingmovies.domain.boundary.UpcomingMoviesService
import com.mauscoelho.upcomingmovies.domain.interactor.GenreServiceImpl
import com.mauscoelho.upcomingmovies.domain.interactor.UpcomingMoviesServiceImpl
import com.mauscoelho.upcomingmovies.infrastructure.boundary.GenreRepository
import com.mauscoelho.upcomingmovies.infrastructure.boundary.UpcomingMoviesRepository
import com.mauscoelho.upcomingmovies.infrastructure.interactor.GenreRepositoryImpl
import com.mauscoelho.upcomingmovies.infrastructure.interactor.UpcomingMoviesRepositoryImpl
import com.mauscoelho.upcomingmovies.infrastructure.network.TmdbNetwork
import com.mauscoelho.upcomingmovies.views.movieDetail.MovieDetailPresenter
import com.mauscoelho.upcomingmovies.views.movieDetail.MovieDetailPresenterImpl
import com.mauscoelho.upcomingmovies.views.movies.MoviesPresenter
import com.mauscoelho.upcomingmovies.views.movies.MoviesPresenterImpl
import com.mauscoelho.upcomingmovies.views.movies.adapters.MoviesAdapter
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.GsonConverterFactory
import retrofit2.Retrofit
import retrofit2.RxJavaCallAdapterFactory
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
    fun provideUpcomingMoviesService(): UpcomingMoviesService = UpcomingMoviesServiceImpl(provideUpcomingMoviesRepository(),provideGenreRepository())

    @Provides
    fun provideGenreService(): GenreService = GenreServiceImpl(provideGenreRepository())

    @Provides
    fun provideUpcomingMoviesRepository(): UpcomingMoviesRepository = UpcomingMoviesRepositoryImpl(provideTmdbNetwork(provideRetrofit()))

    fun provideGenreRepository(): GenreRepository = GenreRepositoryImpl(provideTmdbNetwork(provideRetrofit()),provideGenresCollection())

    @Provides
    fun provideTmdbNetwork(retrofit: Retrofit): TmdbNetwork = retrofit.create(TmdbNetwork::class.java)

    @Provides
    fun provideMoviesAdapter(): MoviesAdapter = MoviesAdapter()

    @Provides
    fun provideLanguage() = "en-US"

    @Provides
    fun provideGenresCollection() = "genres"

}