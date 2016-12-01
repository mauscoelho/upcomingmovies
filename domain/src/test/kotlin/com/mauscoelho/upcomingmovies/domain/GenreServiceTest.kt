package com.mauscoelho.upcomingmovies.domain

import br.ufs.github.rxassertions.RxAssertions
import com.mauscoelho.upcomingmovies.domain.interactor.GenreServiceImpl
import com.mauscoelho.upcomingmovies.infrastructure.boundary.GenreRepository
import com.mauscoelho.upcomingmovies.model.Genre
import com.mauscoelho.upcomingmovies.model.Genres
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
class GenreServiceTest : Spek({
    val genreRepository = mock(GenreRepository::class.java)
    val genreService = GenreServiceImpl(genreRepository)
    val apiKey = "1f54bd990f1cdfb230adb312546d765d"
    val language = "en-US"

    describe("GenreServiceTest") {
        context("Get genres") {

            it("should return genres from service") {
                val expected = Genres(listOf(Genre(1,"Horror"), Genre(2, "Terror")))
                val result = Genres(listOf(Genre(1,"Horror"), Genre(2, "Terror")))

                Mockito.`when`(genreRepository.loadGenres(apiKey, language)).thenReturn(Observable.just(result))

                RxAssertions.assertThat(genreService.loadGenres(apiKey, language))
                        .completes()
                        .withoutErrors()
                        .emissionsCount(1)
                        .expectedSingleValue(expected)
            }



        }
    }
})