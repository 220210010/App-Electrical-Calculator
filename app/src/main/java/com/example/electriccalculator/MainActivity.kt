package com.example.electriccalculator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme{
                Surface(modifier = Modifier.fillMaxSize()) {
                    OhmLawScreen()
                }
            }
        }
    }
}

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
        verticalArrangement = Arrangement.spacedBy((12.dp))
    ) {
        Text("Kalkutalor Hukum Ohm", style = MaterialTheme.typography.headlineSmall)

        OutlinedTextField(
            value = voltageInput,
            onValueChange = { voltageInput = it },
            label = { Text("Tegangan (V) - kosongkan jika dicari") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = currentInput,
            onValueChange = { currentInput = it },
            label = { Text("Arus (I) - Kosongkan jika dicari") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = resistanceInput,
            onValueChange = { resistanceInput = it },
            label = { Text("Resistansi (R) - Kosongkan jika dicari") },
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
                    "Arus (I) = ${OhmLawCalculator.calculateCurrent(v, r)} Ampere"

                r == null && v != null && i != null ->
                    "Resistansi (R) = ${OhmLawCalculator.calculateResistance(v, i)} Ohm"

                else -> "Isi tepat 2 dari 3 kolom, kosongkan 1 yang dingin dicari"
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

