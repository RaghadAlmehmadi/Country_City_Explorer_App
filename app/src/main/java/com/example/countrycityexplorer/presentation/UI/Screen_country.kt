package com.example.countrycityexplorer.presentation.UI

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavController
import com.example.countrycityexplorer.presentation.vm.CountryVM.CountryViewModel
import com.example.countrycityexplorer.ui.theme.blue
import androidx.compose.runtime.*
import com.example.countrycityexplorer.util.Result
import com.example.countrycityexplorer.data.model.Country

import java.net.URLEncoder
import java.nio.charset.StandardCharsets



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CountryListScreen(navController: NavController, viewModel: CountryViewModel) {
    val countryState by viewModel.countryState.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.fetchCountries()
    }

    Scaffold(
        topBar = {
            TopAppBar(
                modifier = Modifier.height(48.dp), // Slightly smaller height
                windowInsets = WindowInsets(0, 0, 0, 0),
                title = { Text("Countries") },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = blue)
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            verticalArrangement = Arrangement.Center
        ) {
            when (val countryState = viewModel.countryState.collectAsState().value) {
                is Result.Loading -> {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        CircularProgressIndicator()
                    }
                }
                is Result.Success -> {
                    LazyColumn {
                        items(countryState.data) { country ->
                            CountryItem(country) {
                                Log.d("NAVIGATION", "Navigating with country: ${country.country}") // ✅ Log name
                                navController.navigate("state_list/${country.country}")
                            }
                        }
                    }
                }
                is Result.Error -> {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        Text("Error: ${countryState.message}", color = Color.Red)
                    }
                }
            }

        }
    }
}



@Composable
fun CountryItem(country: Country, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onClick() },
        colors = CardDefaults.cardColors(containerColor = blue) // Set Card Background to Blue
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "${country.country} (${country.iso2})",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White // Set Text Color to White
            )
        }
    }
}



