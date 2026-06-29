package com.example.electriccalculator

enum class SiPrefix(
    val label: String,
    val symbol: String,
    val factor: Double
) {
    PICO("Piko", "p", 1e-12),
    NANO("Nano", "n", 1e-9),
    MICRO("Mikro", "µ", 1e-6),
    MILLI("Mili", "m", 1e-3),
    BASE("(Basis)", "", 1.0),
    KILO("Kilo", "k", 1e3),
    MEGA("Mega", "M", 1e6),
    GIGA("Giga", "G", 1e9)
}


object SiPrefixConverter {
    fun convert(value: Double, fromPrefix: SiPrefix,toPrefix: SiPrefix): Double{
        // Ubah ke nilai basis dulu, baru ke prefix tujuan
        val baseValue = value * fromPrefix.factor
        return baseValue / toPrefix.factor
    }
}