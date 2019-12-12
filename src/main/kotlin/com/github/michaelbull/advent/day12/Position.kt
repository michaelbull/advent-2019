package com.github.michaelbull.advent.day12

data class Position(
    val x: Int,
    val y: Int,
    val z: Int
) {
    fun translate(x: Int = 0, y: Int = 0, z: Int = 0): Position {
        return copy(
            x = this.x + x,
            y = this.y + y,
            z = this.z + z
        )
    }
}

private val REGEX = Regex("""<x=(.+), y=(.+), z=(.+)>""")

fun String.toPosition(): Position {
    val values = REGEX.matchEntire(this)?.groupValues
    requireNotNull(values) { "$this does not match $REGEX" }

    return Position(
        x = values[1].toInt(),
        y = values[2].toInt(),
        z = values[3].toInt()
    )
}
