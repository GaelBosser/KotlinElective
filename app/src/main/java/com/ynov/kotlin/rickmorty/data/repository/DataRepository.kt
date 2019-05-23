package com.ynov.kotlin.rickmorty.data.repository

import com.ynov.kotlin.rickmorty.data.entity.local.RMCharacter
import com.ynov.kotlin.rickmorty.data.entity.local.RMEpisode
import com.ynov.kotlin.rickmorty.data.manager.ApiManager
import com.ynov.kotlin.rickmorty.data.manager.CacheManager
import io.reactivex.Single

    class DataRepository (private val apiManager: ApiManager, private val cacheManager: CacheManager) {

    fun retrieveCharactersList(): Single<List<RMCharacter>> {

        // TODO Ici mieux vaut ajouter un Single.defer {} pour encapsuler tout ça
        //  cela permet de catcher et remonter les exceptions s'il y en a en dehors des fonctions appelées dans les Managers
        //  par exemple dans le if(), si la liste était null, l'appli crasherait car le NullPointerException ne serait pas catch
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
            // TODO ici on peut utiliser
            //  .doAfterSuccess { cacheManager.cacheCharactersList(0, it) }
            //  après le .map { ... }
            //  plutôt que de gérer le cache dans le même flux
        }
    }

    fun retrieveEpisodesList(): Single<List<RMEpisode>> {
        // TODO de même avec le Single.defer {}
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
            // TODO de même avec
            //  .doAfterSuccess { cacheManager.cacheEpisodesList(0, it) }
        }
    }

    fun retrieveCharacterDetail(id: String): Single<RMCharacter> {
        // TODO de même avec le Single.defer {}
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
            // TODO de même avec
            //  .doAfterSuccess { cacheManager.cacheCharacters(it) }
        }
    }

}