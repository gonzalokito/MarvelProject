package com.example.marvelproject.presentation.fragments.characterlist

import com.example.marvelproject.base.BaseViewState
import com.example.marvelproject.data.model.Character
import java.io.Serializable

data class CharacterListState (

        var characterList: List<Character> = listOf(),
        var limit:Int = 20

        ): BaseViewState()