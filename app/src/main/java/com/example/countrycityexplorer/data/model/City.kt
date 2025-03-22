package com.example.countrycityexplorer.data.model

import com.google.gson.annotations.SerializedName

data class CityRequest(
    @SerializedName("country") val country: String,
    @SerializedName("state") val state: String
)

data class CityResponse(
    @SerializedName("error") val error: Boolean,
    @SerializedName("msg") val message: String,
    @SerializedName("data") val cities: List<String>
)


data class City(
    val cityname: String
)
