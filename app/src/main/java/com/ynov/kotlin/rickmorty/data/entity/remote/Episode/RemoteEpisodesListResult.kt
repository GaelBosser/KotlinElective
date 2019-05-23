package com.ynov.kotlin.rickmorty.data.entity.remote.Episode

import com.google.gson.annotations.SerializedName

class RemoteEpisodesListResult {
    @SerializedName("results") val remoteEpisodesList: List<RemoteEpisode> = emptyList()
}