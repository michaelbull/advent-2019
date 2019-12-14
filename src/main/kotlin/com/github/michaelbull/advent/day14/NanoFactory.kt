package com.github.michaelbull.advent.day14

class NanoFactory(reactions: List<Reaction>) {

    private val reactionsByChemical = reactions.associateBy { it.output.chemical }

    fun oreForOneFuel(): Int {
        return ORE.amountToProduce(ONE_FUEL)
    }

    private fun Chemical.amountToProduce(
        target: ChemicalAmount,
        excess: MutableMap<Chemical, Int> = mutableMapOf()
    ): Int {
        val (inputs, output) = reactionsByChemical.getValue(target.chemical)
        val availableExcess = excess.getOrElse(target.chemical) { 0 }
        val neededAmount = (target.amount - availableExcess).coerceAtLeast(0)
        val repetitions = neededAmount / output.amount + if (neededAmount % output.amount == 0) 0 else 1
        val additionalExcess = output.amount * repetitions - target.amount

        excess[target.chemical] = availableExcess + additionalExcess

        return inputs.sumBy { (chemical, amount) ->
            val required = amount * repetitions

            if (chemical == this) {
                required
            } else {
                val newTarget = ChemicalAmount(chemical, required)
                amountToProduce(newTarget, excess)
            }
        }
    }

    private companion object {
        private val ORE = Chemical("ORE")
        private val FUEL = Chemical("FUEL")
        private val ONE_FUEL = ChemicalAmount(FUEL, 1)
    }
}
