package com.ynov.kotlin.rickmorty.data.entity.remote.Character

data class RemoteCharacter (
    val id: Long,
    val name: String,
    val status: String,
    val species: String,
    val type: String,
    val gender: String,
    val origin: RemoteCharacterLocation,
    val location: RemoteCharacterLocation,
    val image: String,
    val episode: List<String>,
    val url: String,
    val created: String
)