package com.example.electriccalculator

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController

@Composable
fun MenuScreen(navController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    )   {
        Text("Kalkulator Electrical", style = MaterialTheme.typography.headlineSmall)

        Button(
            onClick = { navController.navigate(Routes.OHM_LAW) },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Hukum Ohm")
        }

        Button(
            onClick = { navController.navigate(Routes.RESISTOR) },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Resistor SERI / PARALEL")
        }

        // tambah button lain untuk resistor kapasitor dll
    }
}