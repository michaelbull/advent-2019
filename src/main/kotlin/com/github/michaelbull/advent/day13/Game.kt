package com.github.michaelbull.advent.day13

import com.github.michaelbull.advent.intcode.Intcode
import com.github.michaelbull.advent.intcode.IntcodeComputer

class Game(program: Intcode) {

    var score = 0L
        private set

    var quarters: Long
        get() = computer[0]
        set(value) {
            computer[0] = value
        }

    val blockTiles: Int
        get() = grid.count { (_, tile) -> tile == Tile.Block }

    val beaten: Boolean
        get() = blockTiles == 0

    private sealed class State {
        object ReadX : State()
        object ReadY : State()
        object ReadTileOrScore : State()
    }

    private var state: State = State.ReadX
    private var x = 0L
    private var y = 0L
    private val grid = Grid()

    private val joystickPosition: JoystickPosition
        get() {
            val ballX = grid.position { it == Tile.Ball }.x
            val paddleX = grid.position { it == Tile.HorizontalPaddle }.x
            return ballX.compareTo(paddleX).toJoystickPosition()
        }

    private val computer = IntcodeComputer().apply {
        memory = program

        onInput {
            joystickPosition.toLong()
        }

        onOutput {
            when (state) {
                State.ReadX -> readX(it)
                State.ReadY -> readY(it)
                State.ReadTileOrScore -> readTileOrScore(it)
            }
        }
    }

    suspend fun play() {
        reset()
        computer.compute()
    }

    private fun reset() {
        state = State.ReadX
        x = 0L
        y = 0L
        grid.clear()
    }

    private fun readX(value: Long) {
        x = value
        state = State.ReadY
    }

    private fun readY(value: Long) {
        y = value
        state = State.ReadTileOrScore
    }

    private fun readTileOrScore(value: Long) {
        if (x == -1L && y == 0L) {
            score = value
        } else {
            grid[x, y] = value.toTile()
        }

        state = State.ReadX
    }
}
