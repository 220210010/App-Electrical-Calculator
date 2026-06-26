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

private enum class CapacitorMode(val label: String) {
    SERIES("Seri"),
    PARALLEL("Paralel")
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CapacitorScreen() {
    var selectedMode by remember { mutableStateOf(CapacitorMode.SERIES) }
    var dropdownExpanded by remember { mutableStateOf(false) }

    // List dinamis nilai capacitor mulai dengan 2 input kosong
    val capacitorInput = remember { mutableStateListOf("") }

    var result by remember { mutableStateOf("") }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text("Kalkulator Kapasitor", style = MaterialTheme.typography.headlineSmall)

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
                CapacitorMode.entries.forEach { mode ->
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

        // List input kapasitor dinamis
        LazyColumn(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(capacitorInput.size) { index ->
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ){
                    OutlinedTextField(
                        value = capacitorInput[index],
                        onValueChange = { capacitorInput[index] = it },
                        label = { Text ("R ${index + 1 } (Farad)") },
                        modifier = Modifier.weight(1f)
                    )
                    // Tombol hapus, disembunyikan harus ada 2 input minimal
                    if (capacitorInput.size > 2) {
                        IconButton(onClick = { capacitorInput.removeAt(index)}) {
                            Icon(Icons.Default.Delete, contentDescription = "Hapus")
                        }
                    }
                }
            }
        }

        // Tombol tambah capacitor baru
        OutlinedButton(
            onClick = { capacitorInput.add("") },
            modifier = Modifier.fillMaxWidth()
        ) {
            Icon(Icons.Default.Add, contentDescription = null)
            Spacer(modifier = Modifier.width(8.dp))
            Text("Tambah capacitor")
        }

        Button(
            onClick = {
                val values = capacitorInput.mapNotNull { it.toDoubleOrNull() }
                result = if (values.size < 2) {
                    "Isi minimal 2 nilai capacitor yang valid"
                } else {
                    val total = when (selectedMode) {
                        CapacitorMode.SERIES -> CapacitorCalculator.calculateSeries(values)
                        CapacitorMode.PARALLEL -> CapacitorCalculator.calculateParallel(values)
                    }
                    "Total Kapasitansi = $total Farad"
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Hitung")
        }

        Text(result, style = MaterialTheme.typography.titleMedium)
    }
}