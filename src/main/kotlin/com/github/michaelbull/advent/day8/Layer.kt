package com.github.michaelbull.advent.day8

data class Layer(
    val rows: List<List<Int>>
) {

    constructor(vararg rows: List<Int>) : this(rows.toList())

    init {
        val columnCount = rows.first().size

        require(rows.all { row -> row.size == columnCount }) {
            "all rows must have $columnCount columns"
        }
    }

    operator fun get(x: Int, y: Int): Int {
        return rows[y][x]
    }

    fun prettyPrint(): String {
        val builder = StringBuilder()

        for (rowIndex in rows.indices) {
            val row = rows[rowIndex]

            for (pixel in row) {
                builder.append(pixel.toColor().toChar())
            }

            if (rowIndex != rows.size - 1) {
                builder.append("\n")
            }
        }

        return builder.toString()
    }
}
