package com.github.michaelbull.advent.day10

import com.github.michaelbull.advent.Position

fun readAsteroidMap(): AsteroidMap {
    return ClassLoader.getSystemResourceAsStream("day10.txt")
        .bufferedReader()
        .readText()
        .toAsteroidMap()
}

fun AsteroidMap.part1(): Pair<Position, Int>? {
    return asteroids
        .map { from -> from to detectableAsteroids(from).size }
        .maxBy { (_, asteroids) -> asteroids }
}

fun AsteroidMap.part2(station: Position): Int {
    val asteroid = vaporize(from = station).elementAt(199)
    return asteroid.x * 100 + asteroid.y
}

fun main() {
    val map = readAsteroidMap()
    val (station, detectableAsteroids) = map.part1()!!
    println("part 1 = $detectableAsteroids")
    println("part 2 = ${map.part2(station)}")
}
