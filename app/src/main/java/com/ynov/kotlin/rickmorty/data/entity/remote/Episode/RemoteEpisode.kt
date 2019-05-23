package com.ynov.kotlin.rickmorty.data.entity.remote.Episode

data class RemoteEpisode (
    val id: Long,
    val name: String,
    val air_date: String,
    val episode: String,
    val characters: List<String>,
    val url: String,
    val created: String
)