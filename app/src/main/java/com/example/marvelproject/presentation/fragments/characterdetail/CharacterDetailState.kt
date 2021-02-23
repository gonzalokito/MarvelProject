package com.example.marvelproject.presentation.fragments.characterdetail

import com.example.marvelproject.data.model.Character
import com.example.marvelproject.data.model.ResponseCharacterDataModel
import java.io.Serializable

data class CharacterDetailState(

    var character : Character? = null

):Serializable
