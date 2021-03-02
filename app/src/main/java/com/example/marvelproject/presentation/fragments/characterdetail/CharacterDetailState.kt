package com.example.marvelproject.presentation.fragments.characterdetail

import com.example.marvelproject.base.BaseViewState
import com.example.marvelproject.data.marvel.model.Character
import com.example.marvelproject.data.marvel.model.ResponseCharacterDataModel
import java.io.Serializable

data class CharacterDetailState(

    var character : Character? = null

):BaseViewState()
