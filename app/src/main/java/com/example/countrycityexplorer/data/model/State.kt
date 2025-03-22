package com.example.countrycityexplorer.data.model

import com.google.gson.annotations.SerializedName

// Request for States
data class CountryRequest(
    @SerializedName("country") val country: String
)

data class StateResponse(
    @SerializedName("error") val error: Boolean,
    @SerializedName("msg") val message: String,
    @SerializedName("data") val data: StateData
)

data class StateData(
    @SerializedName("name") val countryName: String?,
    @SerializedName("states") val states: List<State>?
)

data class State(
    @SerializedName("name") val name: String = "Unknown",
    @SerializedName("iso2") val iso2: String? = "N/A",
    @SerializedName("iso3") val iso3: String? = "N/A"
)
