package com.example.electriccalculator

import kotlin.math.PI
import kotlin.math.sqrt

object ACCircuitCalculator {

    // Reaktansi kapasitif: Xc = 1 / (2 * pi * f * c)
    fun calculateCapacitiveReactances(frequency: Double, capacitance: Double): Double {
        return 1.0 / (2 * PI * frequency * capacitance)
    }

    // Reaktansi InduktifL XL = 2 * pi * f * L
    fun calculateInductiveReactances(frequency: Double, inductance: Double): Double {
        return 2 * PI * frequency * inductance
    }

    // Implementasi RLC Seri: Z = sqrt(R^2 + (XL - Xc)^2)
    fun calculateImpedance(resistance: Double, inductiveReactance: Double, capacitiveReactance: Double): Double{
        val reactanceDiff = inductiveReactance - capacitiveReactance
        return sqrt(resistance * resistance + reactanceDiff * reactanceDiff)
    }

    // Faktor daya: cos(phi) = R / Z
    fun calculatePowerFactor(resistance: Double, impedance: Double): Double{
        return resistance/impedance
    }

    // Frekuensi Resonansi: f = 1 / (2 * pi * sqrt(L * C))
    fun calculateResonantFrequency(inductance: Double, capacitance: Double): Double{
        return  1.0 / (2 * PI * sqrt(inductance * capacitance))
    }
}