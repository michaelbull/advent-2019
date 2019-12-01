package com.github.michaelbull.advent.day1

fun readModules(): List<Module> {
    return ClassLoader.getSystemResourceAsStream("day1.txt")
        .bufferedReader()
        .readLines()
        .map { Module(it.toInt()) }
}

fun part1(modules: List<Module>): Int {
    return FuelCounterUpper.totalRequirement(modules)
}

fun part2(modules: List<Module>): Int {
    return RocketEquationDoubleChecker.totalRequirement(modules)
}

fun main() {
    val input = readModules()
    println("part 1 = ${part1(input)}")
    println("part 2 = ${part2(input)}")
}
