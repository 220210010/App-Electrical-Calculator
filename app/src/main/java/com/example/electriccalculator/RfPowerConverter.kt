package com.example.electriccalculator

import kotlin.math.log10
import kotlin.math.pow

object RfPowerConverter {
    fun dbmToWatt(dbm: Double): Double = 10.0.pow((dbm - 30) / 30)
    fun wattToDbm(watt: Double): Double = 10 * log10(watt * 1000)
}