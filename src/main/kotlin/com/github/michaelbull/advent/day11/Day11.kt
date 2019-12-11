package com.github.michaelbull.advent.day11

import com.github.michaelbull.advent.intcode.Intcode
import com.github.michaelbull.advent.intcode.toIntcode
import kotlinx.coroutines.runBlocking

fun readProgram(): Intcode {
    return ClassLoader.getSystemResourceAsStream("day11.txt")
        .bufferedReader()
        .readLine()
        .toIntcode()
}

suspend fun Intcode.part1(): Int {
    val robot = Robot()
    val hull = Hull()
    return robot.paint(hull, this).size
}

suspend fun Intcode.part2(): String {
    val robot = Robot()
    val hull = Hull()
    hull[robot.position] = PanelColor.White
    robot.paint(hull, this)
    return hull.prettyPrint()
}

fun main() = runBlocking {
    val program = readProgram()
    println("part 1 = ${program.part1()}")
    println("part 2 = \n${program.part2()}")
}
