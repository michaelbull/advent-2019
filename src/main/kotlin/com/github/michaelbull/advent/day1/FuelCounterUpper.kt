package com.github.michaelbull.advent.day1

object FuelCounterUpper {

    fun totalRequirement(modules: List<Module>): Int {
        return modules.sumBy(::fuelToLaunch)
    }

    fun fuelToLaunch(module: Module): Int {
        return (module.mass / 3) - 2
    }
}
