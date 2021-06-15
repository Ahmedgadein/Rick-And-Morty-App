package com.ahmedobied.ricknmorty.data.network.common

import com.ahmedobied.ricknmorty.data.network.models.*
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RickAndMortyApiService {

    @GET("character/{id}")
    suspend fun getCharacter(@Path("id") id: Int): CharacterResponse

    @GET("character")
    suspend fun getCharacters(@Query("page") page: Int?): MultipleCharacterResponse

    @GET("location/{id}")
    suspend fun getLocation(@Path("id") id: Int): LocationResponse

    @GET("location")
    suspend fun getLocations(@Query("page") page: Int?): MultipleLocationResponse

    @GET("episode/{id}")
    suspend fun getEpisode(@Path("id") id: Int): EpisodeResponse

    @GET("episode")
    suspend fun getEpisodes(@Query("page") page: Int?): MultipleEpisodeResponse

    companion object {
        operator fun invoke(
            connectivityInterceptor: ConnectivityInterceptor
        ): RickAndMortyApiService {
            val okHttpClient: OkHttpClient = OkHttpClient()
                .newBuilder()
                .addInterceptor(connectivityInterceptor)
                .build()

            return Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl("https://rickandmortyapi.com/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(RickAndMortyApiService::class.java)
        }
    }
}