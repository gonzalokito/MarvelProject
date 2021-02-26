package com.example.marvelproject.presentation.fragments.characterlist

import com.example.marvelproject.base.BaseViewModel
import com.example.marvelproject.data.MarvelRepository

class CharacterListViewModel() : BaseViewModel<CharacterListState>() {

    override val defaulState: CharacterListState= CharacterListState()

    override fun onStartFirstTime() {
        requestInformation()
    }

    fun requestInformation() {
        updatetoLoadingState(CharacterListState(listOf()))

        checkDataState{state ->
        executeCoroutines({

            val response= MarvelRepository().getAllCharacters(state.limit)
            updatetoNormalState(CharacterListState(response))
        },{ error->
            updatetoErrorState(CharacterListState(listOf()),error)

        })
        }
    }

    fun onActionChangeSpinnerValue(itemAtPosition: String) {
        checkDataState {state ->

        }
        //requestInformation(itemAtPosition.toInt())
    }


}