package com.mauscoelho.upcomingmovies.infrastructure


import br.ufs.github.rxassertions.RxAssertions
import com.mauscoelho.upcomingmovies.infrastructure.interactor.SearchRepositoryImpl
import com.mauscoelho.upcomingmovies.infrastructure.network.TmdbNetwork
import com.mauscoelho.upcomingmovies.model.Genre
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

@RunWith(JUnitPlatform::class)
class SearchRepositoryTest : Spek({

    val tmdbNetwork = mock(TmdbNetwork::class.java)
    val searchRepository = SearchRepositoryImpl(tmdbNetwork)

    val apiKey = "1f54bd990f1cdfb230adb312546d765d"
    val language = "en-US"

    describe("SearchRepositoryTest") {
        context("Search movie") {
            it("should return 1 movie") {
                val expected = UpcomingMovies(1, arrayOf(Movie(20, 1, 1, "title", "poster_path", "overview", "release_date", "", "", 0.1, listOf(Genre(1,"Horror")), listOf(1),"a","a","a","a","a")), 4, 1)
                val query = "a"
                val page = 1

                Mockito.`when`(tmdbNetwork.search(query, apiKey, language, 1)).thenReturn(Observable.just(expected))

                RxAssertions.assertThat(searchRepository.search(query, apiKey, language, page))
                        .completes()
                        .withoutErrors()
                        .emissionsCount(1)
                        .expectedSingleValue(expected)
            }
        }

    }
})

