package com.github.michaelbull.advent.day3

import com.github.michaelbull.advent.Position

class Grid(val centralPort: Position = Position.ZERO) : Iterable<Map.Entry<Position, Cell>> {

    private val cells: MutableMap<Position, Cell> = mutableMapOf<Position, Cell>()
        .withDefault { Cell.Empty }
        .apply { set(centralPort, Cell.CentralPort) }

    operator fun get(position: Position): Cell {
        return cells.getValue(position)
    }

    override fun iterator(): Iterator<Map.Entry<Position, Cell>> {
        return cells.iterator()
    }

    fun plot(wire: Wire) {
        var current = centralPort

        for ((index, step) in wire.path.withIndex()) {
            val (direction, length) = step
            val lastStep = index == wire.path.size - 1

            current = when (direction) {
                Direction.Right -> {
                    val startX = current.x + 1
                    val endX = current.x + length
                    wire.plotHorizontally(startX..endX, current.y)
                    current.translate(x = +length)
                }

                Direction.Left -> {
                    val startX = current.x - 1
                    val endX = current.x - length
                    wire.plotHorizontally(startX downTo endX, current.y)
                    current.translate(x = -length)
                }

                Direction.Up -> {
                    val startY = current.y + 1
                    val endY = current.y + length
                    wire.plotVertically(current.x, startY..endY)
                    current.translate(y = +length)
                }

                Direction.Down -> {
                    val startY = current.y - 1
                    val endY = current.y - length
                    wire.plotVertically(current.x, startY downTo endY)
                    current.translate(y = -length)
                }
            }

            val existing = cells[current]
            if (!lastStep && existing != null) {
                cells[current] = when (existing) {
                    is Cell.WireHorizontal -> Cell.WireCorner(existing.wire)
                    is Cell.WireVertical -> Cell.WireCorner(existing.wire)
                    else -> existing
                }
            }
        }
    }

    private fun Wire.plotHorizontally(xRange: Iterable<Int>, y: Int) {
        for (x in xRange) {
            plotIntersectionOr(Position(x, y), Cell::WireHorizontal)
        }
    }

    private fun Wire.plotVertically(x: Int, yRange: Iterable<Int>) {
        for (y in yRange) {
            plotIntersectionOr(Position(x, y), Cell::WireVertical)
        }
    }

    private inline fun Wire.plotIntersectionOr(position: Position, defaultCell: (Wire) -> Cell) {
        val existingId = cells[position]?.wireId()
        val intersected = existingId != null && existingId != id

        cells[position] = if (intersected) {
            Cell.Intersection
        } else {
            defaultCell(this)
        }
    }

    private fun Cell.wireId(): Int? {
        return when (this) {
            is Cell.WireHorizontal -> wire.id
            is Cell.WireVertical -> wire.id
            is Cell.WireCorner -> wire.id
            else -> null
        }
    }
}
