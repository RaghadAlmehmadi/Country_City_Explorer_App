package com.example.countrycityexplorer.domain.usecase

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import com.example.countrycityexplorer.domain.model.Country
import com.example.countrycityexplorer.data.repositoryimp.CountryRepository
import com.example.countrycityexplorer.util.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn
import com.example.countrycityexplorer.domain.mappers.toDomainModel

class GetCountriesUseCase(private val repository: CountryRepository) {
    fun execute(): Flow<Result<List<Country>>> = flow {
        try {
            emit(Result.Loading)
            val countries = repository.getCountries().map { it.toDomainModel() }
            emit(Result.Success(countries))
        } catch (e: Exception) {
            emit(Result.Error(e.message ?: "Unknown error"))
        }
    }.flowOn(Dispatchers.IO)
}
