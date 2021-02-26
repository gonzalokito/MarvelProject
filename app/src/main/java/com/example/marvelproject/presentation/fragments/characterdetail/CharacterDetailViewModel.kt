package com.example.marvelproject.presentation.fragments.characterdetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.marvelproject.base.BaseState
import com.example.marvelproject.base.BaseViewModel
import com.example.marvelproject.data.MarvelRepository
import kotlinx.coroutines.launch

class CharacterDetailViewModel() : BaseViewModel<CharacterDetailState>() {

    override val defaulState: CharacterDetailState= CharacterDetailState()

    override fun onStartFirstTime() {

    }
    fun requestInformation(characterId: Int) {
        updatetoLoadingState(CharacterDetailState(null))
        executeCoroutines({
            val response= MarvelRepository().getCharacter(characterId)
            updatetoNormalState(CharacterDetailState(response))
        },{ error ->
            updatetoErrorState(CharacterDetailState(null),error)
        })
    }


}