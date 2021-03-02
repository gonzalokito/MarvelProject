package com.example.marvelproject.di

import com.example.marvelproject.data.marvel.repository.MarvelRepositoryImpl
import com.example.marvelproject.data.marvel.repository.network.MarvelNetwork
import com.example.marvelproject.domain.repository.MarvelRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    //Vincula interfaz con la implementacion
    @Provides
    fun provideMarvelRepository(network: MarvelNetwork): MarvelRepository = MarvelRepositoryImpl(network)
}