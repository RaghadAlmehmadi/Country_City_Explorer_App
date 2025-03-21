package com.example.countrycityexplorer.presentation.UI


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import com.example.countrycityexplorer.ui.theme.blue

// Define Colors
val White = Color.White // Text Color
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CityListScreen(navController: NavController, backStackEntry: NavBackStackEntry) {
    val stateIso = backStackEntry.arguments?.getString("stateIso") ?: ""

    val filteredCities = remember { cityList.filter { it.cityIso == stateIso } }
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("City in $stateIso", color = White) },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back",
                            tint = White
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = blue)
            )
        }
    ) { paddingValues ->
        Column(
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            Text(
                text = "Cities",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                modifier = Modifier.padding(16.dp)
            )

            LazyColumn {
                items(filteredCities) { city ->
                    CityItem(city)
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
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = city.name,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = White
            )
        }
    }
}

// City Data Model
data class City(
    val id: Int,
    val name: String,
    val cityIso: String,

)

// Sample City List
val cityList = listOf(
    City(1, "Los Angeles", "CA"),
    City(2, "San Francisco", "CA"),
    City(3, "Toronto", "ON"),
    City(4, "Vancouver", "BC"),
    City(5, "Berlin", "BE"),
    City(6, "Munich", "BY"),
    City(7, "Paris", "IDF"),
    City(8, "Marseille", "PAC"),
    City(9, "Tokyo", "JP"),
    City(10, "Osaka", "JP"),
)
