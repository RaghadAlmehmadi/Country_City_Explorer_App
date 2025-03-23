package com.example.countrycityexplorer.data.repositoryimp

import android.util.Log
import com.example.countrycityexplorer.data.apiservice.ApiService
import com.example.countrycityexplorer.data.model.State
import com.example.countrycityexplorer.data.model.StateRequest


class StateRepository(private val apiService: ApiService) {
    suspend fun getStates(country: String, stateCode: String): List<State> {
        val request = StateRequest(country, stateCode)
        Log.d("API_REQUEST", "Sending request: $request")  // Log before making API call

        val response = apiService.getStates(request)

        if (response.isSuccessful && response.body() != null) {
            val stateResponse = response.body()!!
            Log.d("API_RESPONSE", "Full Response: $stateResponse")

            return stateResponse.data?.states?.map { state ->
                State(
                    name = state.name ?: "Unknown State",
                    iso2 = state.iso2 ?: "N/A",
                    iso3 = state.iso3 ?: "N/A"
                )
            } ?: emptyList()
        } else {
            val errorMsg = response.errorBody()?.string() ?: "Unknown error"
            Log.e("API_ERROR", "Failed to fetch states: $errorMsg")
            throw Exception("Failed to fetch states: $errorMsg")
        }
    }
}