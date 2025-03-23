package com.example.countrycityexplorer.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.countrycityexplorer.presentation.UI.CityListScreen
import com.example.countrycityexplorer.presentation.UI.StateListScreen
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.countrycityexplorer.presentation.vm.CountryVM.CountryViewModel
import com.example.countrycityexplorer.domain.usecase.GetCountriesUseCase
import com.example.countrycityexplorer.domain.usecase.GetStatesUseCase
import com.example.countrycityexplorer.domain.usecase.GetCitiesUseCase
import com.example.countrycityexplorer.presentation.UI.CountryListScreen
import com.example.countrycityexplorer.presentation.vm.CityVM.CityViewModel
import com.example.countrycityexplorer.presentation.vm.CityVM.CityViewModelFactory
import com.example.countrycityexplorer.presentation.vm.CountryVM.CountryViewModelFactory
import com.example.countrycityexplorer.presentation.vm.StateVM.StateViewModel
import com.example.countrycityexplorer.presentation.vm.StateVM.StateViewModelFactory
import com.example.countrycityexplorer.ui.theme.CountryCityExplorerTheme
import java.net.URLDecoder
import java.nio.charset.StandardCharsets


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Navapp(
    navController: NavHostController,
    getCountriesUseCase: GetCountriesUseCase,
    getStatesUseCase: GetStatesUseCase,
    getCitiesUseCase: GetCitiesUseCase
) {
    var isDarkTheme by remember { mutableStateOf(false) }

    CountryCityExplorerTheme(darkTheme = isDarkTheme) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text(text = "Color Scheme") },
                    actions = {
                        Switch(
                            checked = isDarkTheme,
                            onCheckedChange = { isDarkTheme = !isDarkTheme }
                        )
                    }
                )
            }
        ) { paddingValues ->
            NavHost(
                navController = navController,
                startDestination = "country_list",
                modifier = Modifier.padding(paddingValues)
            ) {
                composable("country_list") {
                    val viewModel: CountryViewModel = viewModel(
                        factory = CountryViewModelFactory(
                            getCountriesUseCase
                        )
                    )
                    CountryListScreen(navController, viewModel)
                }

                composable("state_list/{countryIso}",) { backStackEntry ->
                    val countryIso = backStackEntry.arguments?.getString("countryIso") ?: ""
                   val stateCode = backStackEntry.arguments?.getString("stateCode") ?: ""
                    val stateViewModel: StateViewModel = viewModel(
                            factory = StateViewModelFactory(getStatesUseCase)
                        )

                        StateListScreen(navController, stateViewModel, countryIso,stateCode)

                }


                composable("city_list/{stateName}/{countryIso}") { backStackEntry ->
                    val stateName = backStackEntry.arguments?.getString("stateName") ?: ""
                    val countryIso = URLDecoder.decode(backStackEntry.arguments?.getString("countryIso") ?: "", StandardCharsets.UTF_8.toString())

                    val cityViewModel: CityViewModel = viewModel(factory = CityViewModelFactory(getCitiesUseCase))
                    CityListScreen(navController, cityViewModel, stateName, countryIso)
                }


            }
        }
    }
}
