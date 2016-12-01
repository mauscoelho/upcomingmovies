package com.mauscoelho.upcomingmovies.infrastructure


import br.ufs.github.rxassertions.RxAssertions
import com.mauscoelho.upcomingmovies.infrastructure.boundary.UpcomingMoviesRepository
import com.mauscoelho.upcomingmovies.model.Movie
import com.mauscoelho.upcomingmovies.model.UpcomingMovies
import com.nhaarman.mockito_kotlin.any
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.context
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.junit.platform.runner.JUnitPlatform
import org.junit.runner.RunWith
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import rx.Observable

@RunWith(JUnitPlatform::class)
class UpcomingMoviesRepositoryTest : Spek({

    val apiKey = "1f54bd990f1cdfb230adb312546d765d"
    val language = "en-US"

    fun createUpcomingMovies(): Observable<UpcomingMovies> {
        val movies = mutableListOf<Movie>()
        (1..20).mapTo(movies) { Movie(1, 1, it, "title", "poster_path", "overview", "release_date", "", "", 0.1, listOf(), listOf()) }
        return Observable.just(UpcomingMovies(1, movies.toTypedArray(), 4, 80))
    }

    fun createMovie(): Observable<Movie> {
        return Observable.just(Movie(20, 1, 1, "title", "poster_path", "overview", "release_date", "", "", 0.1, listOf(), listOf()))
    }

    describe("UpcomingMoviesRepositoryTest") {
        context("Get upcoming movies") {


            it("should return upcoming movies from network") {
                val page = 1
                val upcomingMoviesRepositoryMock = mock(UpcomingMoviesRepository::class.java)
                `when`(upcomingMoviesRepositoryMock.getUpcomingMovies(any(), any(), any())).thenReturn(createUpcomingMovies())

                RxAssertions.assertThat(upcomingMoviesRepositoryMock.getUpcomingMovies(apiKey, language, page))
                        .completes()
                        .withoutErrors()
                        .emissionsCount(1)
            }
        }
        context("Get movie detail") {
            it("should return movie detail from network") {
                val movieId = 1
                val upcomingMoviesRepositoryMock = mock(UpcomingMoviesRepository::class.java)
                `when`(upcomingMoviesRepositoryMock.getMovie(movieId, apiKey, language)).thenReturn(createMovie())

                RxAssertions.assertThat(upcomingMoviesRepositoryMock.getMovie(movieId, apiKey, language))
                        .completes()
                        .withoutErrors()
                        .emissionsCount(1)

            }

            it("should not return movie detail from network") {
                val movieId = 2
                val upcomingMoviesRepositoryMock = mock(UpcomingMoviesRepository::class.java)
                `when`(upcomingMoviesRepositoryMock.getMovie(2, apiKey, language)).thenReturn(Observable.empty())

                RxAssertions.assertThat(upcomingMoviesRepositoryMock.getMovie(movieId, apiKey, language))
                        .emitsNothing()
                        .completes()
                        .withoutErrors()

            }
        }

    }
})
