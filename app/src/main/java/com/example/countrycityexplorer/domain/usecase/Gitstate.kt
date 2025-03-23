package com.example.countrycityexplorer.domain.usecase

import com.example.countrycityexplorer.domain.model.State
import com.example.countrycityexplorer.data.repositoryimp.StateRepository

class GetStatesUseCase(private val repository: StateRepository) {
    suspend fun execute(country: String, stateCode: String): List<State> {
        return repository.getStates(country, stateCode)
    }
}

