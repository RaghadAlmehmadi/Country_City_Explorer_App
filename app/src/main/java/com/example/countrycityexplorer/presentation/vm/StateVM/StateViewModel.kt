package com.example.countrycityexplorer.presentation.vm.StateVM

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.countrycityexplorer.data.model.State
import com.example.countrycityexplorer.domain.usecase.GetStatesUseCase
import com.example.countrycityexplorer.util.Result
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch


class StateViewModel(private val getStatesUseCase: GetStatesUseCase) : ViewModel() {
    private val _stateList = MutableStateFlow<Result<List<State>>>(Result.Loading)
    val stateList: StateFlow<Result<List<State>>> = _stateList

    fun fetchStates(countryIso: String, stateCode: String) {
        viewModelScope.launch {
            try {
                val states = getStatesUseCase.execute(countryIso,stateCode)
                _stateList.value = Result.Success(states)
            } catch (e: Exception) {
                _stateList.value = Result.Error(e.message ?: "An error occurred")
            }
        }
    }
}
