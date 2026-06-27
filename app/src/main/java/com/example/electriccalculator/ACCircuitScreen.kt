package com.example.electriccalculator

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

private enum class ACMode (val label: String) {
    REACTANCE("Reaktansi Xc & XL"),
    IMPEDANCE("Impedansi RLC SERI"),
    RESONANCE("Frekuensi Resonansi")
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ACCircuitScreen() {
    var selectionMode by remember { mutableStateOf(ACMode.REACTANCE) }
    var dropdownExpanded by remember { mutableStateOf(false) }

    // Input field harus berbeda tergantung mode yang dipilih
    var frequencyInput by remember { mutableStateOf("") }
    var capacitanceInput by remember { mutableStateOf("") }
    var inductanceInput by remember { mutableStateOf("") }
    var resistanceInput by remember { mutableStateOf("") }

    var result by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(all = 24.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text("Kalkulator AC Circuit", style = MaterialTheme.typography.headlineSmall)

        ExposedDropdownMenuBox(
            expanded = dropdownExpanded,
            onExpandedChange = { dropdownExpanded = it}
        ) {
            OutlinedTextField(
                value = selectionMode.label,
                onValueChange = {},
                readOnly = true,
                label = { Text("Mode") },
                modifier = Modifier.fillMaxWidth().menuAnchor()
            )
            ExposedDropdownMenu(
                expanded = dropdownExpanded,
                onDismissRequest = {dropdownExpanded = false}
            ) {
            ACMode.entries.forEach { mode ->
                DropdownMenuItem(
                    text = { Text(mode.label) },
                    onClick = {
                        selectionMode = mode
                        dropdownExpanded = false
                        result = "" // reset hasil saat ganti mode
                    }
                )
            }
        }
        }

        //Input Dinamis sesuai dengan mode yang dipilih
        when (selectionMode) {
            ACMode.REACTANCE -> {
                OutlinedTextField(
                    value = frequencyInput,
                    onValueChange = { frequencyInput = it},
                    label = { Text("Frekuensi (Hz)") },
                    modifier = Modifier.fillMaxWidth()
                )
                OutlinedTextField(
                    value = capacitanceInput,
                    onValueChange = { capacitanceInput = it},
                    label = { Text("Kapasitansi (Farad) - Kosongkan jika tidak perlu") },
                    modifier = Modifier.fillMaxWidth()
                )
                OutlinedTextField(
                    value = inductanceInput,
                    onValueChange = { inductanceInput = it},
                    label = { Text("Induktansi (Henry) - Kosongkan jika tidak perlu") },
                    modifier = Modifier.fillMaxWidth()
                )
        }
        ACMode.IMPEDANCE -> {
            OutlinedTextField(
                value = resistanceInput,
                onValueChange = { resistanceInput = it},
                label = {Text("Resistansi R (Ohm)") },
                modifier = Modifier.fillMaxWidth()
            )
            OutlinedTextField(
                value = inductanceInput,
                onValueChange = { inductanceInput = it},
                label = {Text("Reaktansi Induktif XL (Ohm))") },
                modifier = Modifier.fillMaxWidth()
            )
            OutlinedTextField(
                value = capacitanceInput,
                onValueChange = { capacitanceInput = it},
                label = {Text("Reaktansi Kapasitif Xc (Ohm)") },
                modifier = Modifier.fillMaxWidth()
            )
        }
        ACMode.RESONANCE -> {
            OutlinedTextField(
                value = inductanceInput,
                onValueChange = { inductanceInput = it},
                label = {Text("Induktansi (Henry)")},
                modifier = Modifier.fillMaxWidth()
            )
            OutlinedTextField(
                value = capacitanceInput,
                onValueChange = { capacitanceInput = it},
                label = {Text("Kapasitansi (Farad)")},
                modifier = Modifier.fillMaxWidth()
            )
        }
        }

        Button(
            onClick = {
                result = try {
                    when (selectionMode) {
                        ACMode.REACTANCE -> {
                            val f = frequencyInput.toDoubleOrNull()
                            val c = capacitanceInput.toDoubleOrNull()
                            val i = inductanceInput.toDoubleOrNull()

                            val parts = mutableListOf<String>()
                            if (f != null && c != null) {
                                val xc = ACCircuitCalculator.calculateCapacitiveReactances(f,c)
                                parts.add("Xc = $xc Ohm")
                            }
                            if (f != null && i != null) {
                                val xl = ACCircuitCalculator.calculateInductiveReactances(f, i)
                                parts.add("XL = $xl Ohm")
                            }
                            if (parts.isEmpty()) "Isi Frekuensi + minimal 1 dari Kapasistansi/Induktansi"
                            else parts.joinToString("\n")
                        }
                        ACMode.IMPEDANCE -> {
                            val r = resistanceInput.toDoubleOrNull()
                            val xl = inductanceInput.toDoubleOrNull()
                            val xc = capacitanceInput.toDoubleOrNull()
                            if (r == null || xl == null || xc == null) {
                                "Isi semua kolom (R, XL, Xc)"
                            } else {
                                val z = ACCircuitCalculator.calculateImpedance(r, xl, xc)
                                val pf = ACCircuitCalculator.calculatePowerFactor(r, z)
                                "Impedansi Z = $z Ohm\nFaktor Daya = $pf"
                            }
                        }
                        ACMode.RESONANCE -> {
                            val i = inductanceInput.toDoubleOrNull()
                            val c = capacitanceInput.toDoubleOrNull()
                            if (i == null || c == null) {
                                "Isi Induktansi dan Kapasitansi"
                            } else {
                                val f = ACCircuitCalculator.calculateResonantFrequency(i, c)
                                "Frekuensi Resonansi = $f Hz"
                            }
                        }
                    }
                } catch (e: Exception) {
                    "Input tidak valid, cek kembali nilai yang diisi"
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Hitung")
        }

        Text(result, style = MaterialTheme.typography.titleMedium)

    }
}