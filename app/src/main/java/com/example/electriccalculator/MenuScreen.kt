package com.example.electriccalculator

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@Composable
fun MenuScreen(navController: NavHostController) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        item {
            Text(
                "Kalkulator Electrical",
                style = MaterialTheme.typography.headlineSmall
            )
            Spacer(modifier = Modifier.height(8.dp))
        }

        // ── Hukum Dasar ──────────────────────────────
        item {
            CategoryHeader("Hukum Dasar")
        }
        item {
            MenuButton("Hukum Ohm") {
                navController.navigate(Routes.OHM_LAW)
            }
        }
        item {
            MenuButton("AC Circuit (Impedansi & Resonansi)") {
                navController.navigate(Routes.AC_CIRCUIT)
            }
        }

        // ── Komponen Pasif ────────────────────────────
        item {
            Spacer(modifier = Modifier.height(4.dp))
            CategoryHeader("Komponen Pasif")
        }
        item {
            MenuButton("Resistor Seri / Paralel") {
                navController.navigate(Routes.RESISTOR)
            }
        }
        item {
            MenuButton("Kapasitor Seri / Paralel") {
                navController.navigate(Routes.CAPACITOR)
            }
        }
        item {
            MenuButton("Induktor Seri / Paralel") {
                navController.navigate(Routes.INDUCTOR)
            }
        }
        item {
            MenuButton("Kode Warna Resistor") {
                navController.navigate(Routes.RESISTOR_COLOR_CODE)
            }
        }

        // ── Konversi Unit ─────────────────────────────
        item {
            Spacer(modifier = Modifier.height(4.dp))
            CategoryHeader("Konversi Unit")
        }
        item {
            MenuButton("Konversi Watt - HP") {
                navController.navigate(Routes.POWER_CONVERTER)
            }
        }
        item {
            MenuButton("Konversi dBm - Watt") {
                navController.navigate(Routes.RF_POWER_CONVERTER)
            }
        }
        item {
            MenuButton("Konversi Ohm - Siemens") {
                navController.navigate(Routes.CONDUCTANCE_CONVERTER)
            }
        }
        item {
            MenuButton("Konversi Prefix SI") {
                navController.navigate(Routes.SI_PREFIX_CONVERTER)
            }
        }
    }
}

@Composable
private fun CategoryHeader(title: String) {
    Text(
        text = title.uppercase(),
        style = MaterialTheme.typography.labelMedium,
        color = MaterialTheme.colorScheme.secondary,
        modifier = Modifier.padding(vertical = 4.dp)
    )
}

@Composable
private fun MenuButton(label: String, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(label)
    }
}