package com.example.countrycityexplorer.util

sealed class Result<out T> {
    object Loading : Result<Nothing>()
    data class Success<out T>(val data: T) : Result<T>() // Add "out" keyword
    data class Error(val message: String) : Result<Nothing>()
}
