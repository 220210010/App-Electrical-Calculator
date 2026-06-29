package com.example.electriccalculator

object ConductanceConverter{
    fun ohmToSiemens(ohm: Double): Double = 1.0 / ohm
    fun siemensToOhm(siemens: Double): Double = 1.0 / siemens
}