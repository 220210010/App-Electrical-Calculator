package com.example.electriccalculator

enum class ResistorColor(
    val label: String,
    val digit: Int?,         // null kalau warna ini tidak punya nilai digit (misal Emas/Perak)
    val multiplier: Double?, // null kalau warna ini tidak dipakai sebagai multiplier
    val tolerance: String?   // null kalau warna ini tidak dipakai sebagai toleransi
){
    BLACK("Hitam", 0, 1.0, null),
    BROWN("Coklat", 1, 10.0, "±1%"),
    RED("Merah", 2, 100.0, "±2%"),
    ORANGE("Oranye", 3, 1_000.0, null),
    YELLOW("Kuning", 4, 10_000.0, null),
    GREEN("Hijau", 5, 100_000.0, "±0.5%"),
    BLUE("Biru", 6, 1_000_000.0, "±0.25%"),
    VIOLET("Ungu", 7, 10_000_000.0, "±0.1%"),
    GRAY("Abu-abu", 8, null, "±0.05%"),
    WHITE("Putih", 9, null, null),
    GOLD("Emas", null, 0.1, "±5%"),
    SILVER("Perak", null, 0.01, "±10%")
}

object ResistorColorCodeCalculator{
    fun calculateResistance(
        band1: ResistorColor,
        band2: ResistorColor,
        multiplierBand: ResistorColor,
        toleranceBand: ResistorColor
    ): Pair<Double, String> {
        val digit1 =  band1.digit ?: 0
        val digit2 = band2.digit ?: 0
        val multiplier = multiplierBand.multiplier ?: 1.0
        val baseValue = (digit1 * 10 + digit2) * multiplier
        val tolerance = toleranceBand.tolerance ?: "±20%"
        return Pair(baseValue, tolerance)
    }
}

