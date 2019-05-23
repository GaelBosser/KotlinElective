package com.ynov.kotlin.rickmorty.data.entity.local

data class RMCharacter(
    val id: Long,
    val name: String,
    val status: String,
    val species: String,
    val type: String,
    val gender: String,
    val origin: String,
    val location: String,
    val image: String,
    val created: String
)