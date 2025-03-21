package com.example.countrycityexplorer.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.countrycityexplorer.CountryListScreen
import com.example.countrycityexplorer.presentation.UI.CityListScreen
import com.example.countrycityexplorer.presentation.UI.StateListScreen
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.countrycityexplorer.R
import com.example.countrycityexplorer.ui.theme.CountryCityExplorerTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Navapp(navController: NavHostController) {
    var isDarkTheme by remember { mutableStateOf(false) } // Track theme state

    CountryCityExplorerTheme(darkTheme = isDarkTheme) { // Apply Theme
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text(text ="Color Scheme" ) },
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
                    CountryListScreen(navController)
                }

                composable("state_list/{countryIso}") { backStackEntry ->
                    StateListScreen(navController, backStackEntry)
                }

                composable("city_list/{stateIso}") { backStackEntry ->
                    CityListScreen(navController, backStackEntry)
                }
            }
        }
    }
}
