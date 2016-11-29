package com.mauscoelho.upcomingmovies.infraestruture.interactor

import com.mauscoelho.upcomingmovies.infraestruture.boundary.GenresRepository
import com.mauscoelho.upcomingmovies.infraestruture.network.TmdbNetwork
import com.mauscoelho.upcomingmovies.model.Genres
import rx.Observable


class GenresRepositoryImpl(val tmdbNetwork: TmdbNetwork) : GenresRepository {
    override fun getGenres(api_key: String, language: String, ids: Array<Int>): Observable<Genres> {
        return tmdbNetwork.getGenres(api_key, language)
//                .map {
//                    logger.info("teste")
//                    it.genres
//                }
//                .flatMap {
//                    Observable.just(it.filter { true }.toTypedArray())
//                }

//        tmdbNetwork.getGenres(api_key, language).map {
//            genres ->
//            logger.info("genres: ${genres.genres.size}")
//            val selectedIds = mutableListOf<Genre>()
//            ids.forEach { selectedIds.add(genres.genres.get(it)) }
//            selectedIds.toTypedArray()
//        }
    }

}