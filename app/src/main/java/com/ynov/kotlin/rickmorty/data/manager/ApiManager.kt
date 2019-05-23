package com.ynov.kotlin.rickmorty.data.manager

import com.ynov.kotlin.rickmorty.data.entity.remote.Character.RemoteCharacter
import com.ynov.kotlin.rickmorty.data.entity.remote.Character.RemoteCharactersListResult
import com.ynov.kotlin.rickmorty.data.entity.remote.Episode.RemoteEpisode
import com.ynov.kotlin.rickmorty.data.entity.remote.Episode.RemoteEpisodesListResult
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

private const val API_BASE_URL = "https://rickandmortyapi.com/"

class ApiManager {

    private val service: ApiService

    interface ApiService {
        @GET("api/character")
        fun retrieveCharactersList(): Single<RemoteCharactersListResult>

        @GET("api/episode")
        fun retrieveEpisodesList(): Single<RemoteEpisodesListResult>

        @GET("api/character/{id}")
        fun retrieveCharacterDetail(@Path("id") id: String): Single<RemoteCharacter>
    }

    init {
        service = Retrofit.Builder()
            .baseUrl(API_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
            .create(ApiService::class.java)
    }

    fun retrieveCharactersList(): Single<List<RemoteCharacter>> =
        service.retrieveCharactersList().map { it.remoteCharactersList }

    fun retrieveEpisodesList(): Single<List<RemoteEpisode>> =
        service.retrieveEpisodesList().map { it.remoteEpisodesList }

    fun retrieveCharacterDetail(id: String): Single<RemoteCharacter> = service.retrieveCharacterDetail(id)
}