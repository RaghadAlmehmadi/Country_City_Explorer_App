package com.example.countrycityexplorer.presentation.UI

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavController
import androidx.navigation.NavBackStackEntry
import com.example.countrycityexplorer.ui.theme.blue

// Define Colors
// Text Color
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StateListScreen(navController: NavController, backStackEntry: NavBackStackEntry) {
    val countryIso = backStackEntry.arguments?.getString("countryIso") ?: ""
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("States in $countryIso", color = White) },
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
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            LazyColumn {
                items(stateList) { state ->
                    StateItem(state) {
                        navController.navigate("city_list/${state.stateIso}")
                    }
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
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = state.name,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = White
            )
        }
    }
}


// State Data Model
data class State(
    val id: Int,
    val name: String,
    val stateIso: String
)

// Sample State List
val stateList = listOf(
    State(1, "California", "US"),
    State(2, "New York", "US"),
    State(3, "Texas", "US"),
    State(4, "Ontario", "CA"),
    State(5, "Quebec", "CA"),
    State(6, "British Columbia", "CA"),
    State(7, "England", "GB"),
    State(8, "Scotland", "GB"),
    State(9, "Wales", "GB"),
    State(10, "New South Wales", "AU"),
    State(11, "Victoria", "AU"),
    State(12, "Queensland", "AU"),
    State(13, "Bavaria", "DE"),
    State(14, "Hesse", "DE"),
    State(15, "Saxony", "DE"),
)
