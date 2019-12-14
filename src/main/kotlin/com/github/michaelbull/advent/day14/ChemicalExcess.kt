package com.github.michaelbull.advent.day14

class ChemicalExcess {

    private val excess = mutableMapOf<ChemicalName, Long>().withDefault { 0 }

    operator fun set(chemical: ChemicalName, quantity: Long) {
        when {
            quantity == 0L -> excess.remove(chemical)
            quantity > 0L -> excess[chemical] = quantity
            else -> throw IllegalArgumentException("$chemical quantity must be non-negative, was $quantity")
        }
    }

    operator fun get(chemical: ChemicalName): Long {
        return excess.getValue(chemical)
    }
}
