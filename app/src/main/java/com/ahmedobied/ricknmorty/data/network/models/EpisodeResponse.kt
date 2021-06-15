package com.ahmedobied.ricknmorty.data.network.models


import com.google.gson.annotations.SerializedName

data class EpisodeResponse(
    @SerializedName("air_date")
    val airDate: String,
//    val characters: List<Any>,
    val created: String,
    val episode: String,
    val id: Int,
    val name: String,
    val url: String
)