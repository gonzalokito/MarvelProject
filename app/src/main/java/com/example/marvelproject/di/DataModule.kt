package com.example.marvelproject.di

import android.content.Context
import com.example.marvelproject.base.util.NetworkManager
import com.example.marvelproject.data.config.MarvelRetrofit
import com.example.marvelproject.data.marvel.repository.MarvelRepositoryImpl
import com.example.marvelproject.data.marvel.repository.network.MarvelNetwork
import com.example.marvelproject.data.marvel.repository.network.MarvelService
import com.example.marvelproject.domain.repository.MarvelRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    /**
     * Estos Provides solo harian falta si no tiene un Inject su clase
     */
    
    @Provides
    fun provideContext(@ApplicationContext context: Context):Context = context

    @Provides
    fun networkManager(context: Context):NetworkManager = NetworkManager(context)

    @Provides
    fun provideMarvelRetrofit(networkManager: NetworkManager):MarvelRetrofit = MarvelRetrofit(networkManager)

    // Esta hay que implementarla
    @Provides
    fun provideRetrofit(marvelRetrofit: MarvelRetrofit):Retrofit = marvelRetrofit.loadRetrofit()

    // Esta hay que implementarla
    @Provides
    fun provideMarvelService(retrofit: Retrofit):MarvelService = retrofit.create(MarvelService::class.java)

    @Provides
    fun provideMarvelNetwork(marvelService: MarvelService):MarvelNetwork = MarvelNetwork(marvelService)

    @Provides
    fun provideMarvelRepositoryImpl(marvelNetwork: MarvelNetwork):MarvelRepositoryImpl = MarvelRepositoryImpl(marvelNetwork)

    // Esta hay que implementarla
    @Provides
    fun provideMarvelRepository(repositoryImpl: MarvelRepositoryImpl): MarvelRepository = repositoryImpl

}