package com.github.michaelbull.advent.day11

sealed class RobotState {
    object Detect : RobotState()
    object Paint : RobotState()
    object Turn : RobotState()
}
