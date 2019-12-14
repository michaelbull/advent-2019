package com.github.michaelbull.advent.day14

fun readReactions(): List<Reaction> {
    return ClassLoader.getSystemResourceAsStream("day14.txt")
        .bufferedReader()
        .readLines()
        .map(String::toReaction)
}

fun main() {
    val reactions = readReactions()
    val factory = NanoFactory(reactions)
    println("part 1 = ${factory.oreForOneFuel()}")
    println("part 2 = ${factory.producableFuel()}")
}
