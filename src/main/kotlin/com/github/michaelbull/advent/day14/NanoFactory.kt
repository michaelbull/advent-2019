package com.github.michaelbull.advent.day14

class NanoFactory(reactions: List<Reaction>) {

    private val reactionsByChemical = reactions.associateBy { it.output.chemical }

    fun oreForOneFuel(): Long {
        return ORE.amountToProduce(ONE_FUEL)
    }

    fun producableFuel(): Long {
        return FUEL.amountProducedBy(ONE_TRILLION_ORE)
    }

    private fun Chemical.amountToProduce(
        target: ChemicalAmount,
        excess: ChemicalExcess = ChemicalExcess()
    ): Long {
        val availableExcess = excess[target.chemical]
        val excessAfterUsage = availableExcess - target.amount

        return if (excessAfterUsage >= 0L) {
            excess[target.chemical] = excessAfterUsage
            0
        } else {
            val (inputs, output) = reactionsByChemical.getValue(target.chemical)
            val repetitions = (output.amount - excessAfterUsage - 1) / output.amount
            val additionalExcess = (output.amount * repetitions) - target.amount

            excess[target.chemical] = availableExcess + additionalExcess

            inputs.sumByLong { (chemical, amount) ->
                val required = amount * repetitions

                if (chemical == this) {
                    required
                } else {
                    val newTarget = ChemicalAmount(chemical, required)
                    amountToProduce(newTarget, excess)
                }
            }
        }
    }

    private fun Chemical.amountProducedBy(cargo: ChemicalAmount): Long {
        var upperBound = 1L
        while (cargo.chemical.amountToProduce(ChemicalAmount(this, upperBound)) < cargo.amount) {
            upperBound *= 2
        }

        var low = upperBound / 2
        var high = upperBound

        while (low <= high) {
            val mid = (low + high) ushr 1
            val midVal = cargo.chemical.amountToProduce(ChemicalAmount(this, mid))

            if (midVal < cargo.amount) {
                low = mid + 1
            } else if (midVal > cargo.amount) {
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
        private val ORE = Chemical("ORE")
        private val FUEL = Chemical("FUEL")
        private val ONE_FUEL = ChemicalAmount(FUEL, 1)
        private val ONE_TRILLION_ORE = ChemicalAmount(ORE, 1_000_000_000_000)
    }
}
