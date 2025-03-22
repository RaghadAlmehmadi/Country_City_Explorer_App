package com.example.countrycityexplorer.data.repositoryimp

import com.example.countrycityexplorer.data.apiservice.ApiService
import com.example.countrycityexplorer.data.model.*
import com.example.countrycityexplorer.data.apiservice.RetrofitInstance



class CountryRepository(private val apiService: ApiService) {

    suspend fun getCountries(): List<Country> {
        return try {
            val response = RetrofitInstance.apiService.getCountries()
            if (response.isSuccessful && response.body() != null) {
                response.body()!!.data
            } else {
                throw Exception("Failed to fetch countries: ${response.errorBody()?.string()}")
            }
        } catch (e: Exception) {
            e.printStackTrace()
            throw Exception("Network error: ${e.message}")
        }
    }

}

