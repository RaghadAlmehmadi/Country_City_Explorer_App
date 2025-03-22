package com.example.countrycityexplorer.data.repositoryimp

import android.util.Log
import com.example.countrycityexplorer.data.apiservice.ApiService
import com.example.countrycityexplorer.data.model.City
import com.example.countrycityexplorer.data.model.CityRequest

class CityRepository(private val apiService: ApiService) {
    suspend fun getCity(country: String, stateCode: String): List<City> {
        val response = apiService.getCity(CityRequest(country, stateCode))

        if (response.isSuccessful && response.body() != null) {
            val cityResponse = response.body()!!
            Log.d("API_RESPONSE", "Received: ${cityResponse.cities}")

            return cityResponse.cities.map { City(it) }
        } else {
            val errorMsg = response.errorBody()?.string() ?: "Unknown error"
            Log.e("API_ERROR", "Failed to fetch cities: $errorMsg")
            throw Exception("Failed to fetch cities: $errorMsg")
        }
    }
}