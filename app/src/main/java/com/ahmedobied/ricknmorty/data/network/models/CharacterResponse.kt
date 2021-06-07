package com.ahmedobied.ricknmorty.data.network.models


data class CharacterResponse(
    val created: String,
    val episode: List<String>,
    val gender: String,
    val id: Int,
    val image: String,
    val location: LocationResponse,
    val name: String,
    val origin: LocationResponse,
    val species: String,
    val status: String,
    val type: String,
    val url: String
)