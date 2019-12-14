package com.github.michaelbull.advent.day14

data class Reaction(
    val inputs: List<Chemical>,
    val output: Chemical
)

private val REGEX = Regex("""(\d+) ([A-Z]+)""")

private fun chemical(result: MatchResult): Chemical {
    val quantity = result.groupValues[1].toLong()
    val name = ChemicalName(result.groupValues[2])
    return Chemical(name, quantity)
}

fun String.toReaction(): Reaction {
    val matches = REGEX.findAll(this)
    val inputs = matches.take(matches.count() - 1).map(::chemical).toList()
    val output = matches.last().let(::chemical)
    return Reaction(inputs, output)
}
