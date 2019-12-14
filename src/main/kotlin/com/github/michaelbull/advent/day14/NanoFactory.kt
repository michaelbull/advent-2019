package com.github.michaelbull.advent.day14

class NanoFactory(reactions: List<Reaction>) {

    private val reactionsByChemical = reactions.associateBy { it.output.name }

    fun oreForOneFuel(): Long {
        return ORE.amountToProduce(ONE_FUEL)
    }

    fun producableFuel(): Long {
        return FUEL.amountProducedBy(ONE_TRILLION_ORE)
    }

    private fun ChemicalName.amountToProduce(
        chemical: Chemical,
        excess: ChemicalExcess = ChemicalExcess()
    ): Long {
        val availableExcess = excess[chemical.name]
        val excessAfterUsage = availableExcess - chemical.quantity

        return if (excessAfterUsage >= 0L) {
            excess[chemical.name] = excessAfterUsage
            0
        } else {
            val (inputs, output) = reactionsByChemical.getValue(chemical.name)
            val repetitions = (output.quantity - excessAfterUsage - 1) / output.quantity
            val additionalExcess = (output.quantity * repetitions) - chemical.quantity

            excess[chemical.name] = availableExcess + additionalExcess

            inputs.sumByLong { (chemical, quantity) ->
                val amount = quantity * repetitions

                if (chemical == this) {
                    amount
                } else {
                    amountToProduce(chemical * amount, excess)
                }
            }
        }
    }

    private fun ChemicalName.amountProducedBy(chemical: Chemical): Long {
        var upperBound = 1L
        while (chemical.name.amountToProduce(this * upperBound) < chemical.quantity) {
            upperBound *= 2
        }

        var low = upperBound / 2
        var high = upperBound

        while (low <= high) {
            val mid = (low + high) ushr 1
            val midVal = chemical.name.amountToProduce(this * mid)

            if (midVal < chemical.quantity) {
                low = mid + 1
            } else if (midVal > chemical.quantity) {
                high = mid - 1
            }
        }

        return high
    }

    private inline fun <T> Iterable<T>.sumByLong(selector: (T) -> Long): Long {
        var sum = 0L
        for (element in this) {
            sum += selector(element)
        }
        return sum
    }

    private companion object {
        private val ORE = ChemicalName("ORE")
        private val FUEL = ChemicalName("FUEL")
        private val ONE_FUEL = 1L * FUEL
        private val ONE_TRILLION_ORE = 1_000_000_000_000 * ORE
    }
}
