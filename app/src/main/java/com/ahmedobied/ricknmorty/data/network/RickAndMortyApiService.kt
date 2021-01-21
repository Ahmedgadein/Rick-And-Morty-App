package com.ahmedobied.ricknmorty.data.network

import com.ahmedobied.ricknmorty.data.network.models.CharacterResponse
import com.ahmedobied.ricknmorty.data.network.models.MultipleCharacterResponse
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RickAndMortyApiService {

    @GET("character/{id}")
    suspend fun getCharacter(@Path("id") id: Int): CharacterResponse

    @GET("character")
    suspend fun getCharacters(@Query("page") page:Int?): MultipleCharacterResponse

    companion object {
        operator fun invoke(): RickAndMortyApiService {
            return Retrofit.Builder()
                .baseUrl("https://rickandmortyapi.com/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(RickAndMortyApiService::class.java)
        }
    }
}