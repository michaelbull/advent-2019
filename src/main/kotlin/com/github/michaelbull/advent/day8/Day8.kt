package com.github.michaelbull.advent.day8

fun readImage(): Image {
    return ClassLoader.getSystemResourceAsStream("day8.txt")
        .bufferedReader()
        .readLine()
        .toImage(width = 25, height = 6)
}

fun Image.part1(): Int {
    val fewestZerosLayer = layers.minBy { (rows) ->
        rows.flatten().count { pixel -> pixel == 0 }
    } ?: error("No layers in $this")

    val pixels = fewestZerosLayer.rows.flatten()
    val oneDigits = pixels.count { it == 1 }
    val twoDigits = pixels.count { it == 2 }

    return oneDigits * twoDigits
}

fun Image.part2() {
    return decode().prettyPrint()
}

fun String.toImage(width: Int, height: Int): Image {
    val area = width * height

    val layers = windowed(size = area, step = area) { chars ->
        val rows = List(height) { MutableList(width) { 0 } }

        var index = 0
        repeat(height) { y ->
            val row = rows[y]

            repeat(width) { x ->
                val pixel = chars[index]
                row[x] = Character.getNumericValue(pixel)
                index++
            }
        }

        Layer(rows)
    }

    return Image(width, height, layers)
}

fun Image.decode(): Layer {
    val rows = List(height) { MutableList(width) { 0 } }

    repeat(height) { y ->
        val row = rows[y]

        repeat(width) { x ->
            val layer = layers
                .find { it[x, y].toColor() != Color.Transparent }
                ?: layers.last()

            val pixel = layer[x, y]
            row[x] = pixel
        }
    }

    return Layer(rows)
}

fun main() {
    val image = readImage()
    println("part 1 = ${image.part1()}")
    image.part2()
}
