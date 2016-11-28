package com.mauscoelho.upcomingmovies.infraestruture


import br.ufs.github.rxassertions.RxAssertions
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
    describe("UpcomingMoviesRepositoryTest") {
        context("Get upcoming movies") {
            fun getMockUpcomingMovies(): Observable<UpcomingMovies> {
                val movies = arrayOf<Movie>(
                        Movie(1, "original_title", "poster_path", "overview", "release_date"),
                        Movie(2, "original_title", "poster_path", "overview", "release_date"),
                        Movie(3, "original_title", "poster_path", "overview", "release_date"),
                        Movie(4, "original_title", "poster_path", "overview", "release_date"),
                        Movie(5, "original_title", "poster_path", "overview", "release_date"),
                        Movie(6, "original_title", "poster_path", "overview", "release_date"),
                        Movie(7, "original_title", "poster_path", "overview", "release_date"),
                        Movie(8, "original_title", "poster_path", "overview", "release_date"),
                        Movie(9, "original_title", "poster_path", "overview", "release_date"),
                        Movie(10, "original_title", "poster_path", "overview", "release_date"),
                        Movie(11, "original_title", "poster_path", "overview", "release_date"),
                        Movie(12, "original_title", "poster_path", "overview", "release_date"),
                        Movie(13, "original_title", "poster_path", "overview", "release_date"),
                        Movie(14, "original_title", "poster_path", "overview", "release_date"),
                        Movie(15, "original_title", "poster_path", "overview", "release_date"),
                        Movie(16, "original_title", "poster_path", "overview", "release_date"),
                        Movie(17, "original_title", "poster_path", "overview", "release_date"),
                        Movie(18, "original_title", "poster_path", "overview", "release_date"),
                        Movie(19, "original_title", "poster_path", "overview", "release_date"),
                        Movie(20, "original_title", "poster_path", "overview", "release_date")
                )
                return Observable.just(UpcomingMovies(1, movies, 4, 80))
            }

            it("should return upcoming movies from network") {
                val api_key = "1f54bd990f1cdfb230adb312546d765d"
                val language = "en-US"
                val page = 1

                val upcomingMoviesRepositoryMock = mock(UpcomingMoviesRepository::class.java)
                `when`(upcomingMoviesRepositoryMock.getUpcomingMovies(any(), any(), any())).thenReturn(getMockUpcomingMovies())

                RxAssertions.assertThat(upcomingMoviesRepositoryMock.getUpcomingMovies(api_key, language, page))
                        .completes()
                        .withoutErrors()
                        .emissionsCount(1)
            }
        }


    }
})
