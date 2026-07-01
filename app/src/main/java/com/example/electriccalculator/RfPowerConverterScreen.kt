package com.example.electriccalculator

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.unit.dp

@Composable
fun RfPowerConverterScreen() {
    var dbmInput by remember { mutableStateOf("") }
    var wattInput by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text("Konversi dBm - Watt", style = MaterialTheme.typography.headlineSmall)

        OutlinedTextField(
            value = dbmInput,
            onValueChange = { newValue ->
                dbmInput = newValue
                val dbm = newValue.toDoubleOrNull()
                wattInput = if (dbm != null) {
                    "%.6f".format(RfPowerConverter.dbmToWatt(dbm))
                } else ""
            },
            label = { Text("dBm")},
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = wattInput,
            onValueChange = { newValue ->
                wattInput = newValue
                val watt = newValue.toDoubleOrNull()
                dbmInput = if (watt != null) {
                    "%.6f".format(RfPowerConverter.wattToDbm(watt))
                } else ""
            },
            label = { Text("Watt")},
            modifier = Modifier.fillMaxWidth()
        )
    }
}