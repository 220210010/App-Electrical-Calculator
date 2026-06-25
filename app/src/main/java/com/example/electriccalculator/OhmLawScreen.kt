package com.example.electriccalculator

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun OhmLawScreen() {
    var voltageInput by remember { mutableStateOf("") }
    var currentInput by remember { mutableStateOf("") }
    var resistanceInput by remember { mutableStateOf("") }
    var result by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text("Kalkulator Hukum Ohm", style = MaterialTheme.typography.headlineSmall)

        OutlinedTextField(
            value = voltageInput,
            onValueChange = { voltageInput = it},
            label = { Text("Tegangan (V) - Dikonsongkan jika dicari") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = currentInput,
            onValueChange = {currentInput = it},
            label = { Text("Arus (I) - Dikosongkan jika dicari") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = resistanceInput,
            onValueChange = {resistanceInput = it},
            label = { Text("Resistansi (R) - Dikosongkann jika dicari")},
            modifier = Modifier.fillMaxWidth()
        )

        Button(onClick = {
            val v = voltageInput.toDoubleOrNull()
            val i = currentInput.toDoubleOrNull()
            val r = resistanceInput.toDoubleOrNull()

            result = when {
                v == null && i != null && r != null ->
                    "Tegangan (V) = ${OhmLawCalculator.calculateVoltage(i, r)} Volt"
                i == null && v != null && r != null ->
                    "Arus (I)  = ${OhmLawCalculator.calculateCurrent(v, r)} Ampere"
                r == null && i != null && v != null ->
                    "Resistansi (R) = ${OhmLawCalculator.calculateResistance(i, v)} Ohm"
                else -> "Isi tepat 2 dari 3 kolom, kosongkan 1 yang ingin dicari"
            }
        }) {
            Text("Hitung")
        }

        Text(
            text = result,
            style = MaterialTheme.typography.titleMedium
        )
    }




}