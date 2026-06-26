package com.example.electriccalculator

object InductorCalculator {
    // Induktor seri: total = L1 + l2 + ...
    fun calculateSeries(inductances: List <Double>): Double{
        return inductances.sum()
    }

    // Induktor paralel: 1/total = 1/L1 + 1/L2 + ...
    fun calculateParallel(inductances: List<Double>): Double{
        val sumOfReciprocals = inductances.sumOf { 1.0 / it }
        return 1.0 / sumOfReciprocals
    }
}