package com.example.countrycityexplorer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.rememberNavController
import com.example.countrycityexplorer.data.apiservice.RetrofitInstance
import com.example.countrycityexplorer.data.repositoryimp.CityRepository
import com.example.countrycityexplorer.data.repositoryimp.CountryRepository
import com.example.countrycityexplorer.data.repositoryimp.StateRepository
import com.example.countrycityexplorer.domain.usecase.GetCitiesUseCase
import com.example.countrycityexplorer.domain.usecase.GetCountriesUseCase
import com.example.countrycityexplorer.domain.usecase.GetStatesUseCase
import com.example.countrycityexplorer.navigation.Navapp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {


            val repository = CountryRepository(RetrofitInstance.apiService)
            val staterepository = StateRepository(RetrofitInstance.apiService)
            val cityrepository = CityRepository(RetrofitInstance.apiService)


            val getCountriesUseCase = GetCountriesUseCase(repository)
            val getStatesUseCase = GetStatesUseCase(staterepository)
            val getCitiesUseCase = GetCitiesUseCase(cityrepository)

            val navController = rememberNavController()
            Navapp(navController, getCountriesUseCase, getStatesUseCase, getCitiesUseCase)
        }
    }}

