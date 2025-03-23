package com.example.countrycityexplorer.data.model

import com.google.gson.annotations.SerializedName

data class CountryRequest(
    @SerializedName("country") val country: String
)
data class CountryResponse(
    val error: Boolean,
    val msg: String,
    val data: List<Country>
)

data class Country(
    val country: String,
    val iso2: String,
    val iso3: String
)