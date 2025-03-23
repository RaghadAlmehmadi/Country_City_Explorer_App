package com.example.countrycityexplorer.data.apiservice


import com.example.countrycityexplorer.data.model.*
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

import retrofit2.http.GET


interface ApiService {

    @GET("countries")
    suspend fun getCountries(): Response<CountryResponse>

    @POST("countries/states")
    suspend fun getStates(@Body request: StateRequest): Response<StateResponse>

    @POST("countries/state/cities")
    suspend fun getCity(@Body request: CityRequest): Response<CityResponse>

}

