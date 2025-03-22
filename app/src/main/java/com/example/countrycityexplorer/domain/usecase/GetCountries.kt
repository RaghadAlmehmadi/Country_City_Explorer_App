package com.example.countrycityexplorer.domain.usecase


import com.example.countrycityexplorer.data.model.Country
import com.example.countrycityexplorer.data.repositoryimp.CountryRepository
import com.example.countrycityexplorer.util.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetCountriesUseCase(private val repository: CountryRepository) {
    fun execute(): Flow<Result<List<Country>>> = flow {
        emit(Result.Loading)
        try {
            val countries = repository.getCountries()
            emit(Result.Success(countries))
        } catch (e: Exception) {
            emit(Result.Error(e.localizedMessage ?: "Unknown error"))
        }
    }
}








