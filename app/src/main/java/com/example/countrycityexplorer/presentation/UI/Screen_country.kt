package com.example.countrycityexplorer

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavController
import com.example.countrycityexplorer.ui.theme.blue

@Composable
fun CountryListScreen(navController: NavController) {
        Column(
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
        ) {
            Text(
                text = "Country List",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black, // Text in White
                modifier = Modifier.padding(bottom = 16.dp)
            )

            LazyColumn {
                items(countryList) { country ->
                    CountryItem(country) {
                        navController.navigate("state_list/${country.iso2}") // Pass country ISO code
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
                text = "${country.name} (${country.iso2})",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White // Set Text Color to White
            )
        }
    }
}

// Country Data Model
data class Country(
    val id: Int,
    val name: String,
    val iso2: String
)

// Sample Country List
val countryList = listOf(
    Country(1, "United States", "US"),
    Country(2, "Canada", "CA"),
    Country(3, "United Kingdom", "GB"),
    Country(4, "Australia", "AU"),
    Country(5, "Germany", "DE"),
    Country(6, "France", "FR"),
    Country(7, "Italy", "IT"),
)
