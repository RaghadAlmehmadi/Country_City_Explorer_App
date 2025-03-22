package com.example.countrycityexplorer.presentation.UI

import android.util.Log
import androidx.compose.foundation.clickable
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
import com.example.countrycityexplorer.data.model.State
import com.example.countrycityexplorer.presentation.vm.StateVM.StateViewModel
import com.example.countrycityexplorer.ui.theme.blue
import com.example.countrycityexplorer.util.Result
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StateListScreen(
    navController: NavController,
    stateViewModel: StateViewModel,
    countryIso: String // ✅ Passed as a parameter from NavHost
) {
    val stateState by stateViewModel.stateList.collectAsState()

    // Fetch states when the screen loads
    LaunchedEffect(countryIso) {
        stateViewModel.fetchStates(countryIso)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                modifier = Modifier.height(48.dp), // Small top bar
                windowInsets = WindowInsets(0, 0, 0, 0),
                title = { Text("States in $countryIso", color = Color.White) },
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
            when (stateState) {
                is Result.Loading -> {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                }
                is Result.Success -> {
                    val states = (stateState as Result.Success<List<State>>).data ?: emptyList()
                    LazyColumn(
                        modifier = Modifier.fillMaxSize().padding(16.dp)
                    ) {
                        items(states) { state ->
                            StateItem(state) {
                                val safeStateName = URLEncoder.encode(state.name, StandardCharsets.UTF_8.toString())
                                val safeCountryIso = URLEncoder.encode(countryIso, StandardCharsets.UTF_8.toString())
                                navController.navigate("city_list/$safeStateName/$safeCountryIso")
                            }
                        }

                    }
                }
                is Result.Error -> {
                    Text(
                        text = "Error: ${(stateState as Result.Error).message}",
                        color = Color.Red,
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
            }
        }
    }
}

@Composable
fun StateItem(state: State, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onClick() },
        colors = CardDefaults.cardColors(containerColor = blue)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = "${state.name} (${state.iso2})",
                style = MaterialTheme.typography.titleMedium,
                color = Color.White
            )
        }
    }
}
