package com.example.electriccalculator

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun PowerConverterScreen() {
    var wattInput by remember { mutableStateOf("") }
    var hpInput by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text("Konversi Watt - Horsepower", style = MaterialTheme.typography.headlineSmall)

        OutlinedTextField(
            value = wattInput,
            onValueChange = { newValue ->
                wattInput = newValue
                val watt = newValue.toDoubleOrNull()
                hpInput = if (watt != null) {
                    "%.4f".format(PowerConverter.wattToHp(watt))
                } else ""
            },
            label = { Text("Watt")},
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = hpInput,
            onValueChange = { newValue ->
                hpInput = newValue
                val hp = newValue.toDoubleOrNull()
                wattInput = if (hp != null) {
                    "%.4f".format(PowerConverter.hpToWatt(hp))
                } else ""
            },
            label = {Text("Horsepower (Hp)")},
            modifier = Modifier.fillMaxWidth()
        )
    }
}