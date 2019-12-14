package com.github.michaelbull.advent.day14

data class Reaction(
    val inputs: List<ChemicalAmount>,
    val output: ChemicalAmount
)

private val REGEX = Regex("""(\d+) ([A-Z]+)""")

private fun chemicalAmount(result: MatchResult): ChemicalAmount {
    val amount = result.groupValues[1].toLong()
    val chemical = Chemical(result.groupValues[2])
    return ChemicalAmount(chemical, amount)
}

fun String.toReaction(): Reaction {
    val matches = REGEX.findAll(this)
    val inputs = matches.take(matches.count() - 1).map(::chemicalAmount).toList()
    val output = matches.last().let(::chemicalAmount)
    return Reaction(inputs, output)
}
