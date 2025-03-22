package com.example.countrycityexplorer.presentation.vm.CityVM

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.countrycityexplorer.data.model.City
import com.example.countrycityexplorer.domain.usecase.GetCitiesUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import com.example.countrycityexplorer.util.Result


class CityViewModel(
    private val getCitiesUseCase: GetCitiesUseCase
) : ViewModel() {

    private val _cityList = MutableStateFlow<Result<List<City>>>(Result.Loading)
    val cityList: StateFlow<Result<List<City>>> = _cityList

    fun fetchCities(country: String, stateName: String) {
        viewModelScope.launch {
            getCitiesUseCase.execute(country, stateName).collect { result -> // ✅ Pass country
                _cityList.value = result
            }
        }
    }

}


