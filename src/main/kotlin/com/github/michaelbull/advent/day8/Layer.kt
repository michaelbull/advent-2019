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
        return rows.joinToString(separator = "\n") { columns ->
            columns.joinToString(separator = "") { pixel ->
                pixel.toColor().toChar().toString()
            }
        }
    }
}
