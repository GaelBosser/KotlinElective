package com.ynov.kotlin.rickmorty.presentation.main.viewModels

import android.annotation.SuppressLint
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ynov.kotlin.rickmorty.data.entity.local.RMCharacter
import com.ynov.kotlin.rickmorty.presentation.RMApplication
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


class CharactersListViewModel: ViewModel() {

    val charactersList: MutableLiveData<List<RMCharacter>> = MutableLiveData()
    val errorLiveData:  MutableLiveData<Throwable> = MutableLiveData()

    init {
        loadCharactersList()
    }

    @SuppressLint("CheckResult")
    fun loadCharactersList() {
        RMApplication
            .app
            .dataRepository
            .retrieveCharactersList()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                charactersList.postValue(it)
            }, { e ->
                errorLiveData.postValue(e)
            })
    }

}