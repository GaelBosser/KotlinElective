package com.ynov.kotlin.rickmorty.presentation.characterDetail.viewModel

import android.annotation.SuppressLint
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ynov.kotlin.rickmorty.data.entity.local.RMCharacter
import com.ynov.kotlin.rickmorty.presentation.RMApplication
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class CharacterDetailViewModel(private val id: String): ViewModel() {

    val character: MutableLiveData<RMCharacter> = MutableLiveData<RMCharacter>()
    val errorLiveData:  MutableLiveData<Throwable> = MutableLiveData()

    init {
        loadCharacterDetail()
    }

    @SuppressLint("CheckResult")
    fun loadCharacterDetail() {
        RMApplication
            .app
            .dataRepository
            .retrieveCharacterDetail(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                character.postValue(it)
            }, { e ->
                errorLiveData.postValue(e)
            })
    }
}