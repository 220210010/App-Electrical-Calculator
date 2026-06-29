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
    const val POWER_CONVERTER = "power_converter"
    const val RF_POWER_CONVERTER = "rf_power_converter"
    const val CONDUCTANCE_CONVERTER = "conductance_converter"
    const val RESISTOR_COLOR_CODE = "resistor_color_code"
    const val SI_PREFIX_CONVERTER = "si_prefix_converter"
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
        composable (Routes.POWER_CONVERTER) {
            PowerConverterScreen()
        }
        composable (Routes.RF_POWER_CONVERTER) {
            RfPowerConverterScreen()
        }
        composable (Routes.CONDUCTANCE_CONVERTER){
            ConductanceConverterScreen()
        }
        composable (Routes.RESISTOR_COLOR_CODE){
            ResistorColorCodeScreen()
        }
        composable (Routes.SI_PREFIX_CONVERTER){
            SiPrefixConverterScreen()
        }
    }
}
