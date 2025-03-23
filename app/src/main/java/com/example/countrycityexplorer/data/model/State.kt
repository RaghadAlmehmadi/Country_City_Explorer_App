package com.example.countrycityexplorer.data.model

import com.google.gson.annotations.SerializedName

// Request for States

data class StateRequest(
    @SerializedName("country") val country: String,
    @SerializedName("stateCode") val stateCode: String
)

data class StateResponse(
    @SerializedName("error") val error: Boolean,
    @SerializedName("msg") val message: String,
    @SerializedName("data") val data: StateData
)

data class StateData(
    @SerializedName("name") val countryName: String?,
    @SerializedName("states") val states: List<State>?,
    @SerializedName("iso3") val iso3: String
)

data class State(
    @SerializedName("name") val name: String = "Unknown",
    @SerializedName("iso2") val iso2: String? = null,
    @SerializedName("iso3") val iso3: String? = null
)