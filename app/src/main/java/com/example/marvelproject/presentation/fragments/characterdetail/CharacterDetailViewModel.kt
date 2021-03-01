package com.example.marvelproject.presentation.fragments.characterdetail

import com.example.marvelproject.base.BaseViewModel
import com.example.marvelproject.data.MarvelRepository

class CharacterDetailViewModel() : BaseViewModel<CharacterDetailState>() {

    override val defaulState: CharacterDetailState= CharacterDetailState()

    override fun onStartFirstTime() {

    }
    fun requestInformation(characterId: Int) {
        updateToLoadingState()
        executeCoroutines({
            val response= MarvelRepository().getCharacter(characterId)
            updateToNormalState(CharacterDetailState(response))
        },{ error ->
            updateToErrorState(error)
        })
    }


}