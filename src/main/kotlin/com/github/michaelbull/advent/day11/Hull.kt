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
        val (x0, x1) = panels.map { it.key.x }.bounds()
        val (y0, y1) = panels.map { it.key.y }.bounds()

        return (y0..y1).map { y ->
            (x0..x1).map { x ->
                this[Position(x, y)].toChar()
            }.joinToString(separator = "")
        }.reversed().joinToString(separator = "\n")
    }

    private fun Iterable<Int>.bounds(): Pair<Int, Int> {
        return fold(EMPTY_BOUNDS) { (min, max), value ->
            Pair(min.coerceAtMost(value), max.coerceAtLeast(value))
        }
    }

    private companion object {
        private val EMPTY_BOUNDS = Pair(Int.MAX_VALUE, Int.MIN_VALUE)
    }
}
