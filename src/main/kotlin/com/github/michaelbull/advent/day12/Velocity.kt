package com.github.michaelbull.advent.day12

data class Velocity(
    val x: Int,
    val y: Int,
    val z: Int
) {
    fun translate(x: Int = 0, y: Int = 0, z: Int = 0): Velocity {
        return copy(
            x = this.x + x,
            y = this.y + y,
            z = this.z + z
        )
    }

    companion object {
        val ZERO = Velocity(0, 0, 0)
    }
}
