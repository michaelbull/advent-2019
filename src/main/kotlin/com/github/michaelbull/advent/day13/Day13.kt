package com.github.michaelbull.advent.day13

import com.github.michaelbull.advent.intcode.toIntcode
import kotlinx.coroutines.runBlocking

fun readGame(): Game {
    return ClassLoader.getSystemResourceAsStream("day13.txt")
        .bufferedReader()
        .readLine()
        .toIntcode()
        .let(::Game)
}

suspend fun Game.part1(): Int {
    play()
    return blockTiles
}

suspend fun Game.part2(): Long {
    quarters = 2

    while (true) {
        play()

        if (beaten) {
            break
        }
    }

    return score
}

fun main() = runBlocking {
    val game = readGame()
    println("part 1 = ${game.part1()}")
    println("part 2 = ${game.part2()}")
}
