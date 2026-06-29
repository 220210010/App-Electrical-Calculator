package com.example.electriccalculator

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp


// Mapping warna enum ke Color asli Compose, untuk ditampilkan sebagai lingkaran kecil
private fun ResistorColor.toComposeColor(): Color = when (this) {
    ResistorColor.BLACK -> Color(0xFF000000)
    ResistorColor.BROWN -> Color(0xFF8B4513)
    ResistorColor.RED -> Color(0xFFE53935)
    ResistorColor.ORANGE -> Color(0xFFFF9800)
    ResistorColor.YELLOW -> Color(0xFFFFEB3B)
    ResistorColor.GREEN -> Color(0xFF4CAF50)
    ResistorColor.BLUE -> Color(0xFF2196F3)
    ResistorColor.VIOLET -> Color(0xFF9C27B0)
    ResistorColor.GRAY -> Color(0xFF9E9E9E)
    ResistorColor.WHITE -> Color(0xFFFFFFFF)
    ResistorColor.GOLD -> Color(0xFFD4AF37)
    ResistorColor.SILVER -> Color(0xFFC0C0C0)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ColorDropdown(
    label: String,
    selectedColor: ResistorColor,
    options: List<ResistorColor>,
    onColorSelected: (ResistorColor) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = it}
    ) {
        OutlinedTextField(
            value = selectedColor.label,
            onValueChange ={},
            readOnly = true,
            label = { Text(label)},
            leadingIcon = {
                Box(
                    modifier = Modifier
                        .padding(start = 12.dp)
                        .size(20.dp)
                        .background(selectedColor.toComposeColor(), CircleShape)
                )
            },
            modifier = Modifier.fillMaxWidth().menuAnchor()
        )
        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = {expanded = false}
        ) {
           options.forEach { color ->
               DropdownMenuItem(
                   text = {Text(color.label)},
                   leadingIcon = {
                       Box(
                           modifier = Modifier
                               .size(20.dp)
                               .background(color.toComposeColor(), CircleShape)
                       )
                   },
                   onClick = {
                       onColorSelected(color)
                       expanded = false
                   }
               )
           }
        }
    }
}

@Composable
fun ResistorColorCodeScreen() {
    val digitColors = listOf(
        ResistorColor.BLACK, ResistorColor.BROWN, ResistorColor.RED,
        ResistorColor.ORANGE, ResistorColor.YELLOW, ResistorColor.GREEN,
        ResistorColor.BLUE, ResistorColor.VIOLET, ResistorColor.GRAY, ResistorColor.WHITE
    )
    val multiplierColors = listOf(
        ResistorColor.BLACK, ResistorColor.BROWN, ResistorColor.RED,
        ResistorColor.ORANGE, ResistorColor.YELLOW, ResistorColor.GREEN,
        ResistorColor.BLUE, ResistorColor.VIOLET, ResistorColor.GOLD, ResistorColor.SILVER
    )
    val toleranceColors = listOf(
        ResistorColor.BROWN, ResistorColor.RED, ResistorColor.GREEN,
        ResistorColor.BLUE, ResistorColor.VIOLET, ResistorColor.GRAY,
        ResistorColor.GOLD, ResistorColor.SILVER
    )

    var band1 by remember { mutableStateOf(ResistorColor.BROWN) }
    var band2 by remember { mutableStateOf(ResistorColor.BLACK) }
    var mutliplierBand by remember { mutableStateOf(ResistorColor.RED) }
    var toleranceBand by remember { mutableStateOf(ResistorColor.GOLD) }

    val (resistance, tolerance) = ResistorColorCodeCalculator.calculateResistance(
        band1, band2, mutliplierBand, toleranceBand
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text("Kode Warna Resistor", style = MaterialTheme.typography.headlineSmall)

        ColorDropdown(
            label = "Band 1 (Digti 1)",
            selectedColor = band1,
            options = digitColors,
            onColorSelected = {band1 = it}
        )
        ColorDropdown(
            label = "Band 2 (Digit 2)",
            selectedColor = band2,
            options = digitColors,
            onColorSelected = {band2 = it}
        )
        ColorDropdown(
            label = "Band 3 (Multiplier)",
            selectedColor = mutliplierBand,
            options = multiplierColors,
            onColorSelected = {mutliplierBand = it}
        )
        ColorDropdown(
            label = "Band 4 (Tolerance)",
            selectedColor = toleranceBand,
            options = toleranceColors,
            onColorSelected = {toleranceBand = it}
        )

        HorizontalDivider()

        Text(
            text = "Resistansi = $resistance Ohm $tolerance",
            style = MaterialTheme.typography.titleMedium
        )
    }
}