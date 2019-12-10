package com.github.michaelbull.advent.day10

fun readAsteroidMap(): AsteroidMap {
    return ClassLoader.getSystemResourceAsStream("day10.txt")
        .bufferedReader()
        .readText()
        .toAsteroidMap()
}

fun AsteroidMap.part1(): Pair<Position, Int>? {
    return asteroids
        .map { from -> from to detectableAsteroids(from) }
        .maxBy { it.second }
}

fun main() {
    val map = readAsteroidMap()
    println("part 1 = ${map.part1()}")
}
