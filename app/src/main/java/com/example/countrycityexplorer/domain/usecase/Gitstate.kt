package com.example.countrycityexplorer.domain.usecase


import com.example.countrycityexplorer.data.model.State
import com.example.countrycityexplorer.data.repositoryimp.StateRepository


class GetStatesUseCase(private val repository: StateRepository) {
    suspend fun execute(country: String): List<State> {
        return repository.getStates(country)
    }
}


