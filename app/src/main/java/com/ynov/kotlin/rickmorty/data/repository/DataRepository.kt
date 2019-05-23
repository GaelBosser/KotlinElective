package com.ynov.kotlin.rickmorty.data.repository

import com.ynov.kotlin.rickmorty.data.entity.local.RMCharacter
import com.ynov.kotlin.rickmorty.data.entity.local.RMEpisode
import com.ynov.kotlin.rickmorty.data.manager.ApiManager
import com.ynov.kotlin.rickmorty.data.manager.CacheManager
import io.reactivex.Single

    class DataRepository (private val apiManager: ApiManager, private val cacheManager: CacheManager) {

    fun retrieveCharactersList(): Single<List<RMCharacter>> {
        if (cacheManager.getCharactersList(0).isNotEmpty()) {
            return Single.just(cacheManager.getCharactersList(0))
        } else {
            return apiManager.retrieveCharactersList().map {
                val charactersList = it.map { remoteCharacter ->
                    RMCharacter(
                        remoteCharacter.id,
                        remoteCharacter.name,
                        remoteCharacter.status,
                        remoteCharacter.species,
                        remoteCharacter.type,
                        remoteCharacter.gender,
                        remoteCharacter.origin.name,
                        remoteCharacter.location.name,
                        remoteCharacter.image,
                        remoteCharacter.created
                    )
                }
                cacheManager.cacheCharactersList(0, charactersList)
                charactersList
            }
        }
    }

    fun retrieveEpisodesList(): Single<List<RMEpisode>> {
        if (cacheManager.getEpisodesList(0).isNotEmpty()) {
            return Single.just(cacheManager.getEpisodesList(0))
        } else {
            return apiManager.retrieveEpisodesList().map {
                val episodesList = it.map { remoteEpisode ->
                    RMEpisode(
                        remoteEpisode.id,
                        remoteEpisode.name,
                        remoteEpisode.air_date,
                        remoteEpisode.episode,
                        remoteEpisode.characters,
                        remoteEpisode.created,
                        remoteEpisode.url
                    )
                }

                cacheManager.cacheEpisodesList(0, episodesList)
                episodesList
            }
        }
    }

    fun retrieveCharacterDetail(id: String): Single<RMCharacter> {
        if (cacheManager.getCharacterDetail(id.toInt()) != null) {
            return Single.just(cacheManager.getCharacterDetail(id.toInt()))
        } else {
            return apiManager.retrieveCharacterDetail(id).map { remoteCharacter ->
                val character = RMCharacter(
                    remoteCharacter.id,
                    remoteCharacter.name,
                    remoteCharacter.status,
                    remoteCharacter.species,
                    remoteCharacter.type,
                    remoteCharacter.gender,
                    remoteCharacter.origin.name,
                    remoteCharacter.location.name,
                    remoteCharacter.image,
                    remoteCharacter.created
                )

                cacheManager.cacheCharacters(character)
                character
            }
        }
    }

}