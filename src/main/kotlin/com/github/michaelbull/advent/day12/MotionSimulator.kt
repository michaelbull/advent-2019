package com.github.michaelbull.advent.day12

import kotlin.math.sign

fun simulateMotion(moons: List<Moon>) = sequence {
    val simulator = MotionSimulator(moons)

    while (true) {
        simulator.step()
        yield(simulator.moons)
    }
}

class MotionSimulator(moons: List<Moon>) {

    private val _moons = moons.toMutableList()

    val moons: List<Moon>
        get() = _moons

    fun step() {
        _moons.replaceAll(::applyGravity)
        _moons.replaceAll(::applyVelocity)
    }

    private fun applyGravity(moon: Moon): Moon {
        return _moons.fold(moon) { from, to ->
            from.copy(
                velocity = from.velocity.translate(
                    x = (to.position.x - from.position.x).sign,
                    y = (to.position.y - from.position.y).sign,
                    z = (to.position.z - from.position.z).sign
                )
            )
        }
    }

    private fun applyVelocity(moon: Moon): Moon {
        return moon.copy(
            position = moon.position.translate(
                x = moon.velocity.x,
                y = moon.velocity.y,
                z = moon.velocity.z
            )
        )
    }
}
