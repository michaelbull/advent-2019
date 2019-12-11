package com.github.michaelbull.advent.day11

import com.github.michaelbull.advent.Position

class Hull {
    private val panels = mutableMapOf<Position, PanelColor>().withDefault { PanelColor.Black }

    operator fun get(position: Position): PanelColor {
        return panels.getValue(position)
    }

    operator fun set(position: Position, color: PanelColor) {
        panels[position] = color
    }

    fun prettyPrint(): String {
        val xValues = panels.map { it.key.x }
        val yValues = panels.map { it.key.y }
        val x0 = xValues.min()!!
        val x1 = xValues.max()!!
        val y0 = yValues.min()!!
        val y1 = yValues.max()!!

        return (y0..y1).map { y ->
            (x0..x1).map { x ->
                this[Position(x, y)].toChar()
            }.joinToString(separator = "")
        }.reversed().joinToString(separator = "\n")
    }
}
