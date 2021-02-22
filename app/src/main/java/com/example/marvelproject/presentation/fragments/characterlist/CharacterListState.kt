package com.example.marvelproject.presentation.fragments.characterlist

import com.example.marvelproject.data.model.Character
import java.io.Serializable

data class CharacterListState (

        var characterList: List<Character> = listOf()

        ):Serializable