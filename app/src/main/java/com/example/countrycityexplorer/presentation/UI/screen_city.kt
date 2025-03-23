package com.example.countrycityexplorer.presentation.UI

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.navigation.NavController
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.countrycityexplorer.data.model.City
import com.example.countrycityexplorer.presentation.vm.CityVM.CityViewModel
import com.example.countrycityexplorer.ui.theme.blue
import com.example.countrycityexplorer.util.Result

// Define Colors
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CityListScreen(navController: NavController, viewModel: CityViewModel, stateName: String, countryIso: String) {
    val cityState by viewModel.cityList.collectAsState()

    LaunchedEffect(stateName) {
        viewModel.fetchCities(countryIso, stateName)
    }


    Scaffold(
        topBar = {
            TopAppBar(
                modifier = Modifier.height(48.dp), // Slightly smaller height
                windowInsets = WindowInsets(0, 0, 0, 0),
                title = { Text("Cities in $stateName", color = Color.White) },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, "Back", tint = Color.White)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = blue)
            )
        }
    ) { paddingValues ->
        Box(modifier = Modifier.fillMaxSize().padding(paddingValues)) {
            when (cityState) {
                is Result.Loading -> {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                }

                is Result.Success -> {
                    val cities = (cityState as Result.Success<List<City>>).data ?: emptyList()
                    LazyColumn(modifier = Modifier.fillMaxSize().padding(16.dp)) {
                        items(cities) { city ->
                            CityItem(city)
                        }
                    }
                }

                is Result.Error -> {
                    Text(
                        text = "Error: ${(cityState as Result.Error).message}",
                        color = Color.Red,
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
            }
        }
    }
}

@Composable
fun CityItem(city: City) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        colors = CardDefaults.cardColors(blue)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = city.cityname, style = MaterialTheme.typography.titleMedium)
        }
    }
}
