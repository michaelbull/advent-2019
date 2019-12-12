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
    val result = REGEX.matchEntire(this)
    val values = result!!.groupValues.drop(1)
    val (x, y, z) = values.map(String::toInt)
    return Position(x, y, z)
}
