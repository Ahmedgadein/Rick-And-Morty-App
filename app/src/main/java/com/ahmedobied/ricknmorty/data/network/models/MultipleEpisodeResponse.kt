package com.ahmedobied.ricknmorty.data.network.models

import com.google.gson.annotations.SerializedName


data class MultipleEpisodeResponse(
    val info: Info,
    @SerializedName("results")
    val episodes: List<EpisodeResponse>
)