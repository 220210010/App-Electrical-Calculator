package com.example.electriccalculator

object ResistorCalculator {
    // Resistor seri : total = R1 + R2 + R3 + ....
    fun calculateSeries(resistances: List<Double>): Double {
        return resistances.sum()
    }

    // resistor pararel: total = 1/R1 + 1/R2 + 1/R3 + ...
    fun calculateParallel(resistances: List<Double>): Double {
        val sumOfReciprocal = resistances.sumOf { 1.0 / it }
        return 1.0 / sumOfReciprocal
    }
}