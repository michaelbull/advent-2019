package com.github.michaelbull.advent.day14

data class Chemical(
    val name: ChemicalName,
    val quantity: Long
) {
    init {
        require(quantity >= 0L) { "$name quantity must be non-negative, was $quantity" }
    }
}

operator fun Long.times(name: ChemicalName): Chemical {
    return Chemical(name, this)
}

operator fun ChemicalName.times(quantity: Long): Chemical {
    return Chemical(this, quantity)
}
