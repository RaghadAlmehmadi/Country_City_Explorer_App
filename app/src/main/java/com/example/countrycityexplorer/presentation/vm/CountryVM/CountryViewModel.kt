package com.example.countrycityexplorer.presentation.vm.CountryVM

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.countrycityexplorer.domain.usecase.GetCountriesUseCase
import com.example.countrycityexplorer.util.Result
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch



class CountryViewModel(
    private val getCountriesUseCase: GetCountriesUseCase
) : ViewModel() {

    private val _countryState = MutableStateFlow<Result<List<com.example.countrycityexplorer.domain.model.Country>>>(Result.Loading)
    val countryState: StateFlow<Result<List<com.example.countrycityexplorer.domain.model.Country>>> = _countryState

    fun fetchCountries() {
        viewModelScope.launch {
            getCountriesUseCase.execute().collectLatest { result ->
                _countryState.value = result
            }
        }
    }
}









