package com.example.electriccalculator

object PowerConverter {
    private const val WATTS_PER_HP = 745.7

    fun wattToHp (watt: Double): Double = watt / WATTS_PER_HP
    fun hpToWatt (hp: Double): Double = hp * WATTS_PER_HP
}