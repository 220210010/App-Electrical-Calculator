package com.example.electriccalculator

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

private enum class InductorMode(val label: String) {
    SERIES("Seri"),
    PARALLEL("Paralel")
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InductorScreen() {
    var selectedMode by remember { mutableStateOf(InductorMode.SERIES) }
    var dropdownExpanded by remember { mutableStateOf(false) }

    // List dinamis nilai Inductor mulai dengan 2 input kosong
    val InductorInput = remember { mutableStateListOf("") }

    var result by remember { mutableStateOf("") }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text("Kalkulator Inductor", style = MaterialTheme.typography.headlineSmall)

        //Dropdown menu pilih mode seri atau paralel
        ExposedDropdownMenuBox(
            expanded = dropdownExpanded,
            onExpandedChange = {dropdownExpanded = it}
        ) {
            OutlinedTextField(
                value = selectedMode.label,
                onValueChange = {},
                readOnly = true,
                label = {Text("Mode ") },
                modifier = Modifier.fillMaxWidth().menuAnchor()
            )
            ExposedDropdownMenu(
                expanded = dropdownExpanded,
                onDismissRequest = { dropdownExpanded = false}
            ) {
                InductorMode.entries.forEach { mode ->
                    DropdownMenuItem(
                        text = { Text(mode.label) },
                        onClick = {
                            selectedMode = mode
                            dropdownExpanded = false
                        }
                    )
                }
            }
        }

        // List input Inductor dinamis
        LazyColumn(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(InductorInput.size) { index ->
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ){
                    OutlinedTextField(
                        value = InductorInput[index],
                        onValueChange = { InductorInput[index] = it },
                        label = { Text ("R ${index + 1 } (Henry)") },
                        modifier = Modifier.weight(1f)
                    )
                    // Tombol hapus, disembunyikan harus ada 2 input minimal
                    if (InductorInput.size > 2) {
                        IconButton(onClick = { InductorInput.removeAt(index)}) {
                            Icon(Icons.Default.Delete, contentDescription = "Hapus")
                        }
                    }
                }
            }
        }

        // Tombol tambah Inductor baru
        OutlinedButton(
            onClick = { InductorInput.add("") },
            modifier = Modifier.fillMaxWidth()
        ) {
            Icon(Icons.Default.Add, contentDescription = null)
            Spacer(modifier = Modifier.width(8.dp))
            Text("Tambah Inductor")
        }

        Button(
            onClick = {
                val values = InductorInput.mapNotNull { it.toDoubleOrNull() }
                result = if (values.size < 2) {
                    "Isi minimal 2 nilai Inductor yang valid"
                } else {
                    val total = when (selectedMode) {
                        InductorMode.SERIES -> InductorCalculator.calculateSeries(values)
                        InductorMode.PARALLEL -> InductorCalculator.calculateParallel(values)
                    }
                    "Total Induktansi = $total Henry"
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Hitung")
        }

        Text(result, style = MaterialTheme.typography.titleMedium)
    }
}