package com.mauscoelho.upcomingmovies.domain

import br.ufs.github.rxassertions.RxAssertions
import com.mauscoelho.upcomingmovies.domain.interactor.UpcomingMoviesServiceImpl
import com.mauscoelho.upcomingmovies.infrastructure.boundary.GenreRepository
import com.mauscoelho.upcomingmovies.infrastructure.boundary.SearchRepository
import com.mauscoelho.upcomingmovies.infrastructure.boundary.UpcomingMoviesRepository
import com.mauscoelho.upcomingmovies.model.Movie
import com.mauscoelho.upcomingmovies.model.UpcomingMovies
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.context
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.junit.platform.runner.JUnitPlatform
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.mockito.Mockito.mock
import rx.Observable
import rx.Scheduler

@RunWith(JUnitPlatform::class)
class UpcomingMoviesServiceTest : Spek({

    val upcomingMoviesRepository = mock(UpcomingMoviesRepository::class.java)
    val genreRepository = mock(GenreRepository::class.java)
    val searchRepository = mock(SearchRepository::class.java)
    val scheduler = mock(Scheduler::class.java)
    val genreService = UpcomingMoviesServiceImpl(upcomingMoviesRepository, genreRepository, searchRepository, scheduler, scheduler)
    val apiKey = "1f54bd990f1cdfb230adb312546d765d"
    val language = "en-US"

    describe("UpcomingMoviesServiceTest") {
        context("Get movie detail") {
            it("should return movie detail") {
                val movieId = 1
                val expected = Movie(20, 1, 1, "title", "poster_path", "overview", "release_date", "", "", 0.1, listOf(), listOf())
                val result = Movie(20, 1, 1, "title", "poster_path", "overview", "release_date", "", "", 0.1, listOf(), listOf())

                Mockito.`when`(upcomingMoviesRepository.getMovie(movieId, apiKey, language)).thenReturn(Observable.just(result))

                RxAssertions.assertThat(genreService.getMovie(movieId, apiKey, language))
                        .completes()
                        .withoutErrors()
                        .expectedSingleValue(expected)
            }
            it("should return 3 movies") {
                val page = 1
                val expectedMovies =  mutableListOf(
                        Movie(20, 1, 1, "title", "poster_path", "overview", "release_date", "", "", 0.1, listOf(), listOf()),
                        Movie(20, 1, 2, "title", "poster_path", "overview", "release_date", "", "", 0.1, listOf(), listOf()),
                        Movie(20, 1, 3, "title", "poster_path", "overview", "release_date", "", "", 0.1, listOf(), listOf()))

                val result = UpcomingMovies(1, expectedMovies.toTypedArray(), 4, 170)

                Mockito.`when`(upcomingMoviesRepository.getUpcomingMovies(apiKey, language, page)).thenReturn(Observable.just(result))

                RxAssertions.assertThat(genreService.getUpcomingMovies(apiKey, language, page))
                        .completes()
                        .withoutErrors()
                        .emissionsCount(3)

            }
        }
    }
})