package com.ynov.kotlin.rickmorty.data.entity.remote.Character

import com.google.gson.annotations.SerializedName

class RemoteCharactersListResult {
    @SerializedName("results") val remoteCharactersList: List<RemoteCharacter> = emptyList()
}

// TODO Ici on peut utiliser une data class vu que c'est un POJO
//
// data class RemoteCharactersListResult(
//     @SerializedName("results") val remoteCharactersList: List<RemoteCharacter> = emptyList()
// )
