package com.example.electriccalculator.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable

private val WorkbenchColorScheme = darkColorScheme(
    primary = ButtonGreen,        // tombol jadi hijau kalem, bukan neon
    onPrimary = OffWhite,
    secondary = CopperOrange,
    onSecondary = PcbDarkGreen,
    background = PcbDarkGreen,
    onBackground = OffWhite,
    surface = PcbSurface,
    onSurface = OffWhite,
    surfaceVariant = PcbSurface,
    onSurfaceVariant = MutedSage,
    outline = MutedSage
)

@Composable
fun ElectricCalculatorTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = WorkbenchColorScheme,
        content = content
    )
}