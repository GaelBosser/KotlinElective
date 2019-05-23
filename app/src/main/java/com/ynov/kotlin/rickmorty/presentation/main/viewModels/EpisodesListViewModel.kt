package com.ynov.kotlin.rickmorty.presentation.main.viewModels

import android.annotation.SuppressLint
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ynov.kotlin.rickmorty.data.entity.local.RMEpisode
import com.ynov.kotlin.rickmorty.presentation.RMApplication
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class EpisodesListViewModel: ViewModel() {

    val episodesList: MutableLiveData<List<RMEpisode>> = MutableLiveData()
    val errorLiveData: MutableLiveData<Throwable> = MutableLiveData()

    init {
        loadEpisodesList()
    }

    @SuppressLint("CheckResult")
    fun loadEpisodesList() {
        RMApplication
            .app
            .dataRepository
            .retrieveEpisodesList()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                episodesList.postValue(it)
            }, { e ->
                errorLiveData.postValue(e)
            })
    }
}