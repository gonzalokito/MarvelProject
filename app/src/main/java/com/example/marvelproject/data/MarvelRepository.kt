package com.example.marvelproject.data

import com.example.marvelproject.data.model.Character
import com.example.marvelproject.data.model.Comic
import com.example.marvelproject.data.network.MarvelNetwork
import javax.inject.Inject

class MarvelRepository @Inject constructor(private val network: MarvelNetwork) {

    suspend fun getAllCharacters(limit: Int):List<Character>{
        return network.getAllCharacters(limit).data.results
    }

    suspend fun getCharacter(characterId: Int): Character {
        val response= network.getCharacter(characterId).data.results
        return if(response.isNotEmpty()) response[0] else throw NoCharacterException()

    }
    suspend fun getComic(comicId: Int): Comic {
        return network.getComic(comicId).data.results.first()

    }
}

class NoCharacterException : Throwable()

