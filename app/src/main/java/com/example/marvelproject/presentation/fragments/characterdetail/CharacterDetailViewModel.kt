package com.example.marvelproject.presentation.fragments.characterdetail

import com.example.marvelproject.base.BaseViewModel
import com.example.marvelproject.data.MarvelRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CharacterDetailViewModel @Inject constructor(private val repository: MarvelRepository) : BaseViewModel<CharacterDetailState>() {

    override val defaulState: CharacterDetailState= CharacterDetailState()

    override fun onStartFirstTime() {

    }
    fun requestInformation(characterId: Int) {
        updateToLoadingState()
        executeCoroutines({
            val response= repository.getCharacter(characterId)
            updateToNormalState(CharacterDetailState(response))
        },{ error ->
            updateToErrorState(error)
        })
    }


}