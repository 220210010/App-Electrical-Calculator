package com.example.electriccalculator

object OhmLawCalculator {
    // V = I * R -->
    fun calculateVoltage(current: Double, resistance: Double): Double {
        return current * resistance
    }

    // I = V / R
    fun calculateCurrent(voltage: Double, resistance: Double): Double {
        return voltage / resistance
    }

    // R = V / I
    fun calculateResistance(voltage: Double, current: Double): Double {
        return voltage / current
    }

    // P = V * I
    fun calculatePower(voltage: Double, current: Double): Double {
        return voltage * current
    }
}