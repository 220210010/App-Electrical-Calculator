package com.example.electriccalculator

import android.graphics.Outline
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ConductanceConverterScreen() {
    var ohmInput by remember { mutableStateOf("") }
    var siemensInput by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text("Konversi Ohm - Siemens", style = MaterialTheme.typography.headlineSmall)

        OutlinedTextField(
            value = ohmInput,
            onValueChange = { newValue ->
                ohmInput = newValue
                val ohm = newValue.toDoubleOrNull()
                siemensInput = if (ohm != null && ohm != 0.0) {
                    "%.6f".format(ConductanceConverter.ohmToSiemens(ohm))
                } else ""
            },
            label = { Text("Resistansi (Ohm)")},
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = siemensInput,
            onValueChange = { newValue ->
                siemensInput = newValue
                val siemens = newValue.toDoubleOrNull()
                ohmInput = if (siemens != null && siemens != 0.0) {
                    "%.6f".format(ConductanceConverter.siemensToOhm(siemens))
                } else ""
            },
            label = { Text("Kondutivitas (Siemens)")},
            modifier = Modifier.fillMaxWidth()
        )
    }
}