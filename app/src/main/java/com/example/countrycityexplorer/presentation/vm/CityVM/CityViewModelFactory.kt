package com.example.countrycityexplorer.presentation.vm.CityVM

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.countrycityexplorer.domain.usecase.GetCitiesUseCase
import com.example.countrycityexplorer.presentation.city.CityViewModel


class CityViewModelFactory(
    private val getCitiesUseCase: GetCitiesUseCase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CityViewModel::class.java)) {
            return CityViewModel(getCitiesUseCase) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

