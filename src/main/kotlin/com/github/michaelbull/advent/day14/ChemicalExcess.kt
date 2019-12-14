package com.github.michaelbull.advent.day14

class ChemicalExcess {

    private val excess = mutableMapOf<Chemical, Long>().withDefault { 0 }

    operator fun set(chemical: Chemical, amount: Long) {
        when {
            amount == 0L -> excess.remove(chemical)
            amount > 0L -> excess[chemical] = amount
            else -> throw IllegalArgumentException("excess $chemical amount must be non-negative, was $amount")
        }
    }

    operator fun get(chemical: Chemical): Long {
        return excess.getValue(chemical)
    }
}
