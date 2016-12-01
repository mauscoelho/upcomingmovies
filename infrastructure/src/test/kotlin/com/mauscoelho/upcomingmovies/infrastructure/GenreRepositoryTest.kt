package com.mauscoelho.upcomingmovies.infrastructure


import com.mauscoelho.upcomingmovies.infrastructure.network.TmdbNetwork
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.context
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.junit.platform.runner.JUnitPlatform
import org.junit.runner.RunWith
import org.mockito.Mockito.mock

@RunWith(JUnitPlatform::class)
class GenreRepositoryTest : Spek({


    val tmdbNetwork = mock(TmdbNetwork::class.java)
//    val genreRepository = GenreRepositoryImpl(tmdbNetwork, "genres",)

    val apiKey = "1f54bd990f1cdfb230adb312546d765d"
    val language = "en-US"

    describe("GenreRepositoryTest") {

        context("Get genre") {
            it("should return genres from repository by ids") {




//                genreRepository.getGenres(listOf(1,2), apiKey, language)

            }
        }

    }
})
