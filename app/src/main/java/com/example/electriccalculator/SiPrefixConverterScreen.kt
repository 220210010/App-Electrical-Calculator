package com.example.electriccalculator

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun SiPrefixConverterScreen() {
    var valueInput by remember { mutableStateOf("1") }
    var fromPrefix by remember { mutableStateOf(SiPrefix.MICRO) }

    val inputValue = valueInput.toDoubleOrNull()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text("Konversi Prefix SI", style = MaterialTheme.typography.headlineSmall)
        Text(
            "Berlaku untuk satuan apapun (Farad, Henry, Ohm, Watt, dll)",
            style = MaterialTheme.typography.bodySmall
        )

        OutlinedTextField(
            value = valueInput,
            onValueChange = { valueInput = it },
            label = { Text("Nilai") },
            modifier = Modifier.fillMaxWidth()
        )

        Text("Prefix Asal", style = MaterialTheme.typography.labelLarge)

        // Tombol horizontal untuk pilih prefix asal
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(SiPrefix.entries) { prefix ->
                FilterChip(
                    selected = prefix == fromPrefix,
                    onClick = { fromPrefix = prefix },
                    label = { Text(prefix.symbol.ifEmpty { "-" }) }
                )
            }
        }

        HorizontalDivider()

        Text("Hasil di Semua Prefix", style = MaterialTheme.typography.labelLarge)

        if (inputValue == null) {
            Text("Masukkan nilai yang valid", style = MaterialTheme.typography.bodyMedium)
        } else {
            Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                SiPrefix.entries.forEach { targetPrefix ->
                    val converted = SiPrefixConverter.convert(inputValue, fromPrefix, targetPrefix)
                    val isCurrentPrefix = targetPrefix == fromPrefix

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "${targetPrefix.label} (${targetPrefix.symbol.ifEmpty { "-" }})",
                            style = if (isCurrentPrefix) MaterialTheme.typography.titleMedium
                            else MaterialTheme.typography.bodyMedium
                        )
                        Text(
                            text = converted.toString(),
                            style = if (isCurrentPrefix) MaterialTheme.typography.titleMedium
                            else MaterialTheme.typography.bodyMedium
                        )
                    }
                }
            }
        }
    }
}