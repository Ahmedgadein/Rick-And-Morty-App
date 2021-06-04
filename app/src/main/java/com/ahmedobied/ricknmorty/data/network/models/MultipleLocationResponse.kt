package com.ahmedobied.ricknmorty.data.network.models


import com.google.gson.annotations.SerializedName

data class MultipleLocationResponse(
    val info: Info,
    val results: List<LocationResponse>
)