package com.example.marvelproject.data.marvel.repository

import com.example.marvelproject.data.marvel.model.Character
import com.example.marvelproject.data.marvel.model.Comic
import com.example.marvelproject.data.marvel.repository.network.MarvelNetwork
import com.example.marvelproject.domain.repository.MarvelRepository
import javax.inject.Inject

class MarvelRepositoryImpl @Inject constructor(private val network: MarvelNetwork): MarvelRepository {

    override suspend fun getAllCharacters(limit: Int):List<Character>{
        return network.getAllCharacters(limit).data.results
    }

    override suspend fun getCharacter(characterId: Int): Character {
        val response= network.getCharacter(characterId).data.results
        return if(response.isNotEmpty()) response[0] else throw NoCharacterException()

    }
    override suspend fun getComic(comicId: Int): Comic {
        return network.getComic(comicId).data.results.first()

    }
}

class NoCharacterException : Throwable()

