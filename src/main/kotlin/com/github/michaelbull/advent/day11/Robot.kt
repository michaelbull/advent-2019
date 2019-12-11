package com.github.michaelbull.advent.day11

import com.github.michaelbull.advent.Position
import com.github.michaelbull.advent.intcode.Intcode
import com.github.michaelbull.advent.intcode.IntcodeComputer

class Robot {

    var position = Position.ZERO
        private set

    var direction: Direction = Direction.Up
        private set

    suspend fun paint(hull: Hull, program: Intcode): Set<Position> {
        var state: RobotState = RobotState.Painting
        val paintedPanels = mutableSetOf<Position>()
        val computer = IntcodeComputer()

        computer.memory = program

        computer.onInput {
            when (hull[position]) {
                PanelColor.Black -> 0
                PanelColor.White -> 1
            }
        }

        computer.onOutput { value ->
            when (state) {
                RobotState.Painting -> {
                    hull[position] = value.toPanelColor()
                    paintedPanels += position
                    state = RobotState.Turning
                }

                RobotState.Turning -> {
                    turn(value.toTurnInstruction())
                    move()
                    state = RobotState.Painting
                }
            }
        }

        computer.compute()

        return paintedPanels
    }

    private fun move() {
        position = when (direction) {
            Direction.Up -> position.translate(y = 1)
            Direction.Right -> position.translate(x = 1)
            Direction.Down -> position.translate(y = -1)
            Direction.Left -> position.translate(x = -1)
        }
    }

    private fun turn(instruction: TurnInstruction) {
        return when (instruction) {
            TurnInstruction.Left -> turnLeft()
            TurnInstruction.Right -> turnRight()
        }
    }

    private fun turnLeft() {
        direction = when (direction) {
            Direction.Up -> Direction.Left
            Direction.Left -> Direction.Down
            Direction.Down -> Direction.Right
            Direction.Right -> Direction.Up
        }
    }

    private fun turnRight() {
        direction = when (direction) {
            Direction.Up -> Direction.Right
            Direction.Right -> Direction.Down
            Direction.Down -> Direction.Left
            Direction.Left -> Direction.Up
        }
    }
}
