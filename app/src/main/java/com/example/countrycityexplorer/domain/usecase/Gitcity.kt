package com.example.countrycityexplorer.domain.usecase

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import com.example.countrycityexplorer.domain.model.City
import com.example.countrycityexplorer.data.repositoryimp.CityRepository
import com.example.countrycityexplorer.util.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn
import com.example.countrycityexplorer.domain.mappers.toDomainModel

class GetCitiesUseCase(private val repository: CityRepository) {
    fun execute(country: String, stateName: String): Flow<Result<List<City>>> = flow {
        try {
            emit(Result.Loading)
            val cities = repository.getCity(country, stateName).map { it.toDomainModel() }
            emit(Result.Success(cities))
        } catch (e: Exception) {
            emit(Result.Error(e.message ?: "Unknown error"))
        }
    }.flowOn(Dispatchers.IO)
}
