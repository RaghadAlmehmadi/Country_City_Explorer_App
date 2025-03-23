package com.example.countrycityexplorer.data.repositoryimp

import android.util.Log
import com.example.countrycityexplorer.data.apiservice.ApiService
import com.example.countrycityexplorer.data.model.StateRequest
import com.example.countrycityexplorer.domain.mappers.toDomainModel

class StateRepository(private val apiService: ApiService) {
    suspend fun getStates(country: String, stateCode: String): List<com.example.countrycityexplorer.domain.model.State> {
        val request = StateRequest(country, stateCode)
        Log.d("API_REQUEST", "Sending request: $request")

        val response = apiService.getStates(request)

        if (response.isSuccessful && response.body() != null) {
            val stateResponse = response.body()!!
            Log.d("API_RESPONSE", "Full Response: $stateResponse")

            return stateResponse.data?.states?.map { state ->
                state.toDomainModel()
            } ?: emptyList()
        } else {
            val errorMsg = response.errorBody()?.string() ?: "Unknown error"
            Log.e("API_ERROR", "Failed to fetch states: $errorMsg")
            throw Exception("Failed to fetch states: $errorMsg")
        }
    }

}
