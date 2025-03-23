package com.example.countrycityexplorer.presentation.vm.StateVM

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.countrycityexplorer.domain.usecase.GetStatesUseCase
import com.example.countrycityexplorer.presentation.state.StateViewModel


class StateViewModelFactory(private val getStatesUseCase: GetStatesUseCase) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(StateViewModel::class.java)) {
            return StateViewModel(getStatesUseCase) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
