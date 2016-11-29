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
                        Movie(1, 1,1,"original_title", "poster_path", "overview", "release_date", arrayOf(), arrayOf()),
                        Movie(2, 1,1,"original_title", "poster_path", "overview", "release_date", arrayOf(), arrayOf()),
                        Movie(3, 1,1,"original_title", "poster_path", "overview", "release_date", arrayOf(), arrayOf()),
                        Movie(4, 1,1,"original_title", "poster_path", "overview", "release_date", arrayOf(), arrayOf()),
                        Movie(5, 1,1,"original_title", "poster_path", "overview", "release_date", arrayOf(), arrayOf()),
                        Movie(6, 1,1,"original_title", "poster_path", "overview", "release_date", arrayOf(), arrayOf()),
                        Movie(7, 1,1,"original_title", "poster_path", "overview", "release_date", arrayOf(), arrayOf()),
                        Movie(8, 1,1,"original_title", "poster_path", "overview", "release_date", arrayOf(), arrayOf()),
                        Movie(9, 1,1,"original_title", "poster_path", "overview", "release_date", arrayOf(), arrayOf()),
                        Movie(10, 1,1,"original_title", "poster_path", "overview", "release_date", arrayOf(), arrayOf()),
                        Movie(11, 1,1,"original_title", "poster_path", "overview", "release_date", arrayOf(), arrayOf()),
                        Movie(12, 1,1,"original_title", "poster_path", "overview", "release_date", arrayOf(), arrayOf()),
                        Movie(13, 1,1,"original_title", "poster_path", "overview", "release_date", arrayOf(), arrayOf()),
                        Movie(14, 1,1,"original_title", "poster_path", "overview", "release_date", arrayOf(), arrayOf()),
                        Movie(15, 1,1,"original_title", "poster_path", "overview", "release_date", arrayOf(), arrayOf()),
                        Movie(16, 1,1,"original_title", "poster_path", "overview", "release_date", arrayOf(), arrayOf()),
                        Movie(17, 1,1,"original_title", "poster_path", "overview", "release_date", arrayOf(), arrayOf()),
                        Movie(18, 1,1,"original_title", "poster_path", "overview", "release_date", arrayOf(), arrayOf()),
                        Movie(19, 1,1,"original_title", "poster_path", "overview", "release_date", arrayOf(), arrayOf()),
                        Movie(20, 1,1,"original_title", "poster_path", "overview", "release_date", arrayOf(), arrayOf())
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
