package com.ahmedobied.ricknmorty.data.network.models


import com.ahmedobied.ricknmorty.data.network.models.CharacterResponse
import com.ahmedobied.ricknmorty.data.network.models.Info
import com.google.gson.annotations.SerializedName

data class MultipleCharacterResponse(
    val info: Info,
    @SerializedName("results")
    val characters: List<CharacterResponse>
)