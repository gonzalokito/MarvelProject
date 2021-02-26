package com.example.marvelproject.presentation.fragments.characterlist

import com.example.marvelproject.base.BaseViewModel
import com.example.marvelproject.data.MarvelRepository

class CharacterListViewModel() : BaseViewModel<CharacterListState>() {

    override val defaulState: CharacterListState= CharacterListState()

    override fun onStartFirstTime() {
        requestInformation()
    }

    private fun requestInformation() {
        updateToLoadingState()

        checkDataState{state ->
        executeCoroutines({
            val response= MarvelRepository().getAllCharacters(state.limit)
            updateToNormalState(state.copy(characterList = response))
        },{ error->
            updateToErrorState(error)
        })
        }
    }

    fun onActionChangeSpinnerValue(limit: String) {
        checkDataState {state ->
            if(state.limit!=limit.toInt()) {
                updateDataState(state.copy(limit = limit.toInt()))
                requestInformation()
            }
        }

    }

}