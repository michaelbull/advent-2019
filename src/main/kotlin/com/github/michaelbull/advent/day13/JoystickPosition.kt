package com.github.michaelbull.advent.day13

sealed class JoystickPosition {
    object Neutral : JoystickPosition()
    object TiltedLeft : JoystickPosition()
    object TiltedRight : JoystickPosition()
}

fun Int.toJoystickPosition(): JoystickPosition {
    return when {
        this == 0 -> JoystickPosition.Neutral
        this < 0 -> JoystickPosition.TiltedLeft
        this > 0 -> JoystickPosition.TiltedRight
        else -> error("Unknown position $this")
    }
}

fun JoystickPosition.toLong(): Long {
    return when (this) {
        JoystickPosition.Neutral -> 0
        JoystickPosition.TiltedLeft -> -1
        JoystickPosition.TiltedRight -> 1
    }
}
