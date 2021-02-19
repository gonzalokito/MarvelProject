package com.example.marvelproject.data.network

import com.example.marvelproject.BuildConfig
import com.example.marvelproject.base.util.tomd5
import com.example.marvelproject.data.model.ResponseAllCharactersDataModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class MarvelNetwork {

    lateinit var service: MarvelService

    private fun loadRetrofit(){
        var retrofit = Retrofit.Builder()
            .baseUrl("https://gateway.marvel.com")
            .addConverterFactory(GsonConverterFactory.create())
            .client(createHttpClient())
            .build()

        service = retrofit.create(MarvelService::class.java)
    }
     suspend fun getAllCharacters(): ResponseAllCharactersDataModel {
        loadRetrofit()
         return service.getAllCharacters()
    }

    private fun createHttpClient():OkHttpClient{

        //Create HTTP client
        val builder = OkHttpClient.Builder()
            .connectTimeout(90L, TimeUnit.SECONDS)
            .readTimeout(90L, TimeUnit.SECONDS)
            .writeTimeout(90L,TimeUnit.SECONDS)

        // Logger Interceptor
        val loggerInterceptor = HttpLoggingInterceptor()
        //Imprime los logs en el logCat si el Build Variant esta en Debug
        loggerInterceptor.level = if(BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
        builder.addInterceptor(loggerInterceptor)

        //App Token
        val hash=((System.currentTimeMillis() /1000).toString()+BuildConfig.PRIVATE_KEY+BuildConfig.PUBLIC_KEY).tomd5()

        builder.addInterceptor{ chain ->
            //Nos permite acceder a la URL
            var request= chain.request()
            val url= request.url.newBuilder()
                .addQueryParameter("apikey",BuildConfig.PUBLIC_KEY)
                .addQueryParameter("hash", hash)
                .addQueryParameter("ts",(System.currentTimeMillis() /1000).toString())
                .build()
            request=request.newBuilder().url(url).build()
            chain.proceed(request)
        }
        return builder.build()
    }



}