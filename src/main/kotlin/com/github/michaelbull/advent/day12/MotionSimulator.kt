package com.github.michaelbull.advent.day12

import kotlin.math.sign

fun simulateMotion(planet: Planet) = sequence {
    val simulator = MotionSimulator(planet)

    while (true) {
        simulator.step()
        yield(simulator.current)
    }
}

class MotionSimulator(initial: Planet) {

    var current: Planet = initial
        private set

    fun step() {
        applyGravity()
        applyVelocity()
    }

    private fun applyGravity() {
        current = current.copy(
            moons = current.moons.map(::applyGravity)
        )
    }

    private fun applyVelocity() {
        current = current.copy(
            moons = current.moons.map(::applyVelocity)
        )
    }

    private fun applyGravity(moon: Moon): Moon {
        return current.moons.fold(moon, ::applyGravityBetween)
    }

    private fun applyGravityBetween(from: Moon, to: Moon): Moon {
        return from.copy(
            velocity = from.velocity.translate(
                x = (to.position.x - from.position.x).sign,
                y = (to.position.y - from.position.y).sign,
                z = (to.position.z - from.position.z).sign
            )
        )
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
