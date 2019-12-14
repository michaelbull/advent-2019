package com.github.michaelbull.advent.day14

class NanoFactory(reactions: List<Reaction>) {

    private val reactionsByChemical = reactions.associateBy { it.output.chemical }

    fun oreForOneFuel(): Long {
        return ORE.amountToProduce(ONE_FUEL)
    }

    fun producableFuel(): Long {
        return ONE_TRILLION_ORE.producableAmountOf(FUEL)
    }

    private fun Chemical.amountToProduce(
        target: ChemicalAmount,
        excess: MutableMap<Chemical, Long> = mutableMapOf()
    ): Long {
        val (inputs, output) = reactionsByChemical.getValue(target.chemical)
        val availableExcess = excess.getOrElse(target.chemical) { 0L }
        val neededAmount = (target.amount - availableExcess).coerceAtLeast(0)
        val repetitions = neededAmount / output.amount + if (neededAmount % output.amount == 0L) 0 else 1
        val additionalExcess = output.amount * repetitions - target.amount

        excess[target.chemical] = availableExcess + additionalExcess

        return inputs.sumByLong { (chemical, amount) ->
            val required = amount * repetitions

            if (chemical == this) {
                required
            } else {
                val newTarget = ChemicalAmount(chemical, required)
                amountToProduce(newTarget, excess)
            }
        }
    }

    private fun ChemicalAmount.producableAmountOf(target: Chemical): Long {
        var upperBound = 1L
        while (chemical.amountToProduce(ChemicalAmount(target, upperBound)) < amount) {
            upperBound *= 2
        }

        var low = upperBound / 2
        var high = upperBound

        while (low <= high) {
            val mid = (low + high) ushr 1
            val midVal = chemical.amountToProduce(ChemicalAmount(target, mid))

            if (midVal < amount) {
                low = mid + 1
            } else if (midVal > amount) {
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
