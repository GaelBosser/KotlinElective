package com.ynov.kotlin.rickmorty.data.manager

import android.annotation.SuppressLint
import com.ynov.kotlin.rickmorty.data.entity.local.RMCharacter
import com.ynov.kotlin.rickmorty.data.entity.local.RMEpisode

class CacheManager {

    @SuppressLint("UseSparseArrays")
    private var charactersList = HashMap<Int, List<RMCharacter>>()
    @SuppressLint("UseSparseArrays")
    private var episodesList = HashMap<Int, List<RMEpisode>>()
    private var characterDetail = mutableListOf<RMCharacter>()

    fun cacheCharactersList(page: Int, charactersList: List<RMCharacter>) {
        this.charactersList[page] = charactersList
    }

    fun cacheEpisodesList(page: Int, charactersList: List<RMEpisode>) {
        this.episodesList[page] = charactersList
    }

    fun cacheCharacters(character: RMCharacter) {
        this.characterDetail.add(character)
    }

    fun getCharactersList(page: Int): List<RMCharacter> = charactersList[page] ?: listOf()  // TODO emptyList() plutôt que listOf()
    fun getEpisodesList(page: Int): List<RMEpisode> = episodesList[page] ?: listOf()        // TODO emptyList() plutôt que listOf()
    fun getCharacterDetail(id: Int): RMCharacter? = characterDetail.find { it.id == id.toLong() }
}