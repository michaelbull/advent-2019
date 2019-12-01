package com.github.michaelbull.advent.day1

object RocketEquationDoubleChecker {

    fun totalRequirement(modules: List<Module>): Int {
        return modules.sumBy { fuelToLaunch(it) }
    }

    tailrec fun fuelToLaunch(module: Module, currentFuel: Int = 0): Int {
        require(currentFuel >= 0)

        val moduleFuel = (module.mass / 3) - 2

        return if (moduleFuel < 0) {
            currentFuel
        } else {
            fuelToLaunch(Module(moduleFuel), currentFuel + moduleFuel)
        }
    }
}
