package com.mauscoelho.upcomingmovies.infrastructure


import br.ufs.github.rxassertions.RxAssertions
import com.fasterxml.jackson.databind.ObjectMapper
import com.mauscoelho.upcomingmovies.infrastructure.interactor.GenreRepositoryImpl
import com.mauscoelho.upcomingmovies.infrastructure.network.TmdbNetwork
import com.mauscoelho.upcomingmovies.model.Genre
import com.snappydb.DB
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.context
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.junit.platform.runner.JUnitPlatform
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.mockito.Mockito.mock

@RunWith(JUnitPlatform::class)
class GenreRepositoryTest : Spek({

    val genresCollection = "genres"
    val snappyDb = mock(DB::class.java)
    val mapper = mock(ObjectMapper::class.java)
    val tmdbNetwork = mock(TmdbNetwork::class.java)
    val genreRepository = GenreRepositoryImpl(tmdbNetwork, genresCollection , snappyDb, mapper)

    val apiKey = "1f54bd990f1cdfb230adb312546d765d"
    val language = "en-US"

    val genreJson = "[{\"id\":28,\"name\":\"Action\"},{\"id\":12,\"name\":\"Adventure\"},{\"id\":16,\"name\":\"Animation\"},{\"id\":35,\"name\":\"Comedy\"},{\"id\":80,\"name\":\"Crime\"},{\"id\":99,\"name\":\"Documentary\"},{\"id\":18,\"name\":\"Drama\"},{\"id\":10751,\"name\":\"Family\"},{\"id\":14,\"name\":\"Fantasy\"},{\"id\":36,\"name\":\"History\"},{\"id\":27,\"name\":\"Horror\"},{\"id\":10402,\"name\":\"Music\"},{\"id\":9648,\"name\":\"Mystery\"},{\"id\":10749,\"name\":\"Romance\"},{\"id\":878,\"name\":\"Science Fiction\"},{\"id\":10770,\"name\":\"TV Movie\"},{\"id\":53,\"name\":\"Thriller\"},{\"id\":10752,\"name\":\"War\"},{\"id\":37,\"name\":\"Western\"}]"


    describe("GenreRepositoryTest") {
        context("Get genre") {
            it("should return genres from repository by ids") {

                val expected = listOf(Genre(1,"Horror"), Genre(2,"Terror"))

                Mockito.`when`(snappyDb.get(genresCollection)).thenReturn(genreJson)
                Mockito.`when`(mapper.readValue(genreJson, Array<Genre>::class.java)).thenReturn(arrayOf(Genre(1, "Horror"), Genre(2, "Terror")))

                RxAssertions.assertThat(genreRepository.getGenres(listOf(1, 2), apiKey, language))
                        .completes()
                        .withoutErrors()
                        .emissionsCount(1)
                        .expectedSingleValue(expected)
            }
        }

    }
})

