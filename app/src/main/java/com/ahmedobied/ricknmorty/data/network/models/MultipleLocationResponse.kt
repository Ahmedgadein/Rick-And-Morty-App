package com.ahmedobied.ricknmorty.data.network.models


import com.google.gson.annotations.SerializedName

data class MultipleLocationResponse(
    val info: Info,
    @SerializedName("results")
    val locations: List<LocationResponse>
)