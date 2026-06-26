package com.example.electriccalculator

object CapacitorCalculator {
    // Kapasitor seri: 1/total = 1/C1 + 1/C2 + ... kebalikan resistor
    fun calculateSeries(capacitances: List <Double>): Double{
        val sumOfReciprocals = capacitances.sumOf { 1.0/ it }
        return 1.0 / sumOfReciprocals
    }

    //kapsitor paralel: total = C1 + C2 + ...
    fun calculateParallel(capacitances: List<Double>): Double{
        return capacitances.sum()
    }
}