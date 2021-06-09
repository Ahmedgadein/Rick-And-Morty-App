package com.ahmedobied.ricknmorty.data.network.models



data class Info(
    val count: Int,
    val next: String?,
    val pages: Int,
    val prev: String?
)