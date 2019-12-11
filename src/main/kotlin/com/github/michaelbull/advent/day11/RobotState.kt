package com.github.michaelbull.advent.day11

sealed class RobotState {
    object Painting : RobotState()
    object Turning : RobotState()
}
