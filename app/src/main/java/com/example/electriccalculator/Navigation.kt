package com.example.electriccalculator

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

// Daftar semua "alamat" screen di app ini
object Routes{
    const val MENU = "menu"
    const val OHM_LAW = "ohm_law"
    const val RESISTOR = "resistor"
    const val CAPACITOR = "capasitor"
    const val INDUCTOR = "induktor"
    const val AC_CIRCUIT = "ac_circuit"
}

@Composable
fun AppNavigation() {
    val navController: NavHostController = rememberNavController()

    NavHost(navController = navController, startDestination = Routes.MENU){
        composable(Routes.MENU){
            MenuScreen(navController = navController)
        }
        composable(Routes.OHM_LAW){
            OhmLawScreen()
        }
        composable(Routes.RESISTOR){
            ResistorScreen()
        }
        composable(Routes.CAPACITOR){
            CapacitorScreen()
        }
        composable(Routes.INDUCTOR){
            InductorScreen()
        }
        composable (Routes.AC_CIRCUIT) {
            ACCircuitScreen()
        }
    }
}
