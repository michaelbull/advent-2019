package com.github.michaelbull.advent.day11

sealed class PanelColor {
    object Black : PanelColor()
    object White : PanelColor()
}

fun Long.toPanelColor(): PanelColor {
    return when (this) {
        0L -> PanelColor.Black
        1L -> PanelColor.White
        else -> error("Unknown color $this")
    }
}

fun PanelColor.toChar(): Char {
    return when (this) {
        PanelColor.Black -> ' '
        PanelColor.White -> '#'
    }
}
