package com.github.michaelbull.advent.day3

fun readWires(): List<Wire> {
    return ClassLoader.getSystemResourceAsStream("day3.txt")
        .bufferedReader()
        .readLines()
        .toWires()
}

fun List<String>.toWires(): List<Wire> {
    return mapIndexed { index, path -> path.toWire(index) }
}

fun Grid.part1(): Int? {
    return asSequence()
        .filter { it.value is Cell.Intersection }
        .map { it.key distanceTo centralPort }
        .min()
}

fun Grid.part2(wires: List<Wire>): Int? {
    return asSequence()
        .filter { it.value is Cell.Intersection }
        .map { wires.stepsToIntersect(centralPort, it.key) }
        .min()
}

fun main() {
    val wires = readWires()
    val grid = Grid()
    wires.forEach(grid::plot)
    println("part 1 = ${grid.part1()}")
    println("part 2 = ${grid.part2(wires)}")
}
